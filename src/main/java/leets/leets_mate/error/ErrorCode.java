package leets.leets_mate.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INPUT_FORMAT_ERROR(400, "INPUT_FORMAT_ERROR","입력이 올바르지 않습니다"),
    GROUP_SIZE_ERROR(400, "GROUP_SIZE_ERROR", "짝 수가 최대 인원수 보다 많습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;

//    ErrorCode(int httpStatus, String code, String message) {
//        this.httpStatus = httpStatus;
//        this.code = code;
//        this.message = message;
//    }
}
