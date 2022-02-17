package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.common.Constants2;
import com.github.zhangyingwei.solid.common.SolidUtils2;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.result.NumResult2;
import com.github.zhangyingwei.solid.result.SolidResult2;
import com.github.zhangyingwei.solid.result.WowResult2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IFProcessBlock2 extends ProcessBlock2 {

    private List<IfItem2> ifItem2s = new ArrayList<>();
    private List<IfControlItem2> controlItem2s = new ArrayList<>();
    private List<ElsIFProcessBlock2> elseIfBlock = new ArrayList<>();


    public IFProcessBlock2(String topMark, SolidContext2 context2) {
        super(topMark, context2);
        super.tag = Constants2.TAG_IF;
        super.endTag = Constants2.TAG_IF_END;

    }

    @Override
    public Block2 setFlag(boolean flag) {
        return null;
    }

    @Override
    public SolidResult2 render() {
        return null;
    }

    class IfItem2 extends SolidIfItem2 {
        SolidResult2 first;

        SolidResult2 second;

        String symbol;

        public IfItem2(String template) {
            super(template.trim());
        }

    }


    abstract class SolidIfItem2 {
        String template;

        public SolidIfItem2(String template) {
            this.template = template;
        }
    }

    class IfItem extends SolidIfItem2 {

        SolidResult2 first;
        SolidResult2 second;
        String symbol;

        public IfItem(String template) {
            super(template);
        }

        // if a== b ; a>b ; a<b   or  if a.b
        void formate() {
            String templateText = template.replaceFirst(tag, "").replaceAll(" ", "");
            if (templateText.contains("=") || templateText.contains(">") || templateText.contains("<")) {
                String spliter = "";
                if (templateText.contains("==")) {
                    spliter = "==";
                } else if (templateText.contains("!=")) {
                    spliter = "!=";
                } else if (templateText.contains(">=")) {
                    spliter = ">=";
                } else if (templateText.contains("<=")) {
                    spliter = "<=";
                } else if (templateText.contains(">")) {
                    spliter = ">";
                } else if (templateText.contains("<")) {
                    spliter = "<";
                }
                String[] params = templateText.split(spliter);
                this.first = SolidUtils2.getFromPlaceHolderOrNot(context2, params[0]);
                this.second = SolidUtils2.getFromPlaceHolderOrNot(context2, params[1]);
                this.symbol = spliter;
            } else {
                this.first = SolidUtils2.getFromPlaceHolderOrNot(context2, templateText);
                this.second = null;
            }
        }

        // if a condition
        IfItemCompare2 valid() {
            this.formate();
            if (second == null) {
                if (first instanceof WowResult2) {
                    return new IfItemCompare2(false);
                } else {
                    return new IfItemCompare2(true);
                }
            } else if (first instanceof SolidResult2 && second instanceof SolidResult2) {
                if (first instanceof WowResult2 || second instanceof WowResult2) {
                    return new IfItemCompare2(false);
                } else if (this.getFirst() instanceof NumResult2 || this.getSecond() instanceof NumResult2) {
                    BigDecimal bfrist = new BigDecimal(this.getFirst().getResult().toString());
                    BigDecimal bsecond = new BigDecimal(this.getSecond().getResult().toString());
                    if (this.symbol.equals("==")) {
                        return new IfItemCompare2(
                                bfrist.compareTo(bsecond) == 0
                        );
                    } else if (this.symbol.equals("!=")) {
                        return new IfItemCompare2(
                                bfrist.compareTo(bsecond) != 0
                        );
                    } else if (this.symbol.equals(">")) {
                        return new IfItemCompare2(
                                bfrist.compareTo(bsecond) == 1
                        );
                    } else if ((this.symbol.equals("<"))) {
                        return new IfItemCompare2(
                                bfrist.compareTo(bsecond) == -1
                        );
                    } else if (this.symbol.equals(">=")) {
                        return new IfItemCompare2(
                                bfrist.compareTo(bsecond) == 1
                        ).orWith(new IfItemCompare2(bfrist.compareTo(bsecond) == 0)
                        );
                    } else if (this.symbol.equals("<=")) {
                        return new IfItemCompare2(
                                bfrist.compareTo(bsecond) == -1
                        ).orWith(
                                new IfItemCompare2(bfrist.compareTo(bsecond) == 0)
                        );
                    }
                } else {
                    if (this.symbol.equals("==")) {
                        return new IfItemCompare2(this.getFirst().getResult().equals(this.getSecond().getResult()));
                    } else if (this.symbol.equals("!=")) {
                        return new IfItemCompare2(!this.getFirst().getResult().equals(this.getSecond().getResult()));
                    } else if (this.symbol.equals(">")) {
                        return new IfItemCompare2(this.getFirst().getResult().toString().compareTo(this.getSecond().getResult().toString()) > 0);
                    } else if (this.symbol.equals("<")) {
                        return new IfItemCompare2(this.getFirst().getResult().toString().compareTo(this.getSecond().getResult().toString()) < 0);
                    }
                }
            }
            return new IfItemCompare2(false);
        }

        public SolidResult2 getFirst() {
            return first;
        }

        public SolidResult2 getSecond() {
            return second;
        }
    }

    class IfItemCompare2 {
        Boolean flag;

        public IfItemCompare2(Boolean flag) {
            this.flag = flag;
        }

        IfItemCompare2 andWith(IfItemCompare2 other) {
            return new IfItemCompare2(this.flag && other.flag);
        }

        IfItemCompare2 orWith(IfItemCompare2 other) {
            return new IfItemCompare2(this.flag || other.flag);
        }
    }

    class IfControlItem2 extends SolidIfItem2 {

        public IfControlItem2(String template) {
            super(template.trim().toLowerCase(Locale.ROOT));
        }

        IfItemCompare2 compare2(IfItemCompare2 before, IfItemCompare2 after) {
            if ("and".equals(super.template)) {
                return before.andWith(after);
            } else if ("or".equals(super.template)) {
                return before.orWith(after);
            }
            return new IfItemCompare2(false);
        }
    }
}