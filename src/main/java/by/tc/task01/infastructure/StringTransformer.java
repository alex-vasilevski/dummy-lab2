package by.tc.task01.infastructure;

import com.google.common.base.CaseFormat;

public class StringTransformer {
    public String transformUpperUnderscore2LowerCamel(String source){
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, source);
    }

    public String transformLowerUnderscore2LowerCamel(String source){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, source);
    }

    public String transformLowerCamel2LowerUnderscore(String source){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, source);
    }

    public String transformUpperCamel2LowerUnderscore(String source){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, source);
    }

    public String transformUpperUnderscore2UpperCamel(String source){
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, source);
    }

    public String transformLowerCamel2UpperUnderscore(String source) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, source);
    }
}
