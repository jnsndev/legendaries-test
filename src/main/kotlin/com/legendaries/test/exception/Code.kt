package com.legendaries.test.exception

enum class Code(val message: String) {

    // 공통
    INVALID_REQUEST("유효하지 않은 요청입니다."),

    // 팀
    TEAM_NAME_ALREADY_IN_USE("이미 사용되고 있는 팀명입니다."),
    NOT_FOUND_TEAM("해당 팀을 찾을 수 없습니다."),
    NOT_DELETE_TEAM("해당 팀을 삭제할 수 없습니다.")


    // 크루
}