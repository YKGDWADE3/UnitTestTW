package tw.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * Created by jxzhong on 2017/5/18.
 */
public class InputValidator {
    public Boolean validate(String numStr) {
        List<String> numList = numStrToList(numStr);
        int NumCount = 4;
        Boolean isValidate = validateDigitsCount(numList, NumCount);
        return isValidate && validateSingleDigit(numList, NumCount);
    }

    private boolean validateSingleDigit(List<String> numList, int numCount) {
        try {
            return numList.stream()
                    .map(num -> parseInt(num))
                    .distinct()
                    .filter(num -> num < 10).count() == numCount;
        }catch (NumberFormatException exception){
            return false;
        }
    }

    private Boolean validateDigitsCount(List<String> numList, int numCount) {
        return numList.size() == numCount;
    }

    private List<String> numStrToList(String numStr) {
        return Arrays.stream(numStr.split(" "))
                .collect(Collectors.toList());
    }
}
