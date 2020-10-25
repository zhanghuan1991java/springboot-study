/**
 * 记录页面上用户行为
 * @param userId
 * @param page
 * @param operate
 */
function report_data(userId, page, operate) {
    var reportJson = {};
    reportJson.user_id = userId;
    reportJson["operate_time"] = (new Date()).toJSON();
    reportJson.page = page;
    reportJson.operate = operate;

    $.ajax({
        type: "post",
        url: "/userBehaviorReport",
        async: true,
        data: JSON.stringify(reportJson),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
        }
    });
}