$(document).ready(function () {
    $('#saveCourse').click(function (e) {
        e.preventDefault();

        $(this).prop('disabled', true);

        let json = {"name": $('#name').val(), "description": $('#description').val()};

        sendCourseData("POST", "/course/create", json, "Creation");
    });

    $('#updateCourse').click(function (e) {
        e.preventDefault();

        $(this).prop('disabled', true);

        let courseId = $('#courseId').val();

        let json = {"id": courseId, "name": $('#name').val(), "description": $('#description').val()};

        sendCourseData("PUT", "/course/update/" + courseId, json, "Update");
    });

    $('.deleteCourse').click(function (e) {
        e.preventDefault();

        $(this).prop('disabled', true);

        let courseId = $(this).val();

        deleteCourse(courseId);
    });

    function sendCourseData(type, url, jsonData, successMsg) {
        $.ajax({
            type: type,
            url: url,
            data: JSON.stringify(jsonData),
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                let respContent = "<div class='successAlert alert alert-success'>" +
                    "<span class='success'>" + successMsg + " <b>" + data.name + "</b> was successfully</span></div>";

                setTimeout(function () {
                    window.location.href = "/course"
                }, 2000);

                $("#successAlert").html(respContent).show();
            },
            error: function (xhr) {
                let respContent = "<div class='errorAlert alert alert-danger'>" +
                    "<span class='error'>" + xhr.responseText + "</span></div>";

                setTimeout(function () {
                    $('#saveCourse').prop('disabled', false);
                    $('#updateCourse').prop('disabled', false);
                    $("#errorAlert").hide();
                }, 2000);

                $("#errorAlert").html(respContent).show();

                console.log("Error processing course", xhr.responseText);
            }
        });
    }

    function deleteCourse(courseId) {
        $.ajax({
            type: "DELETE",
            url: "/course/delete/" + courseId,
            success: function () {
                window.location.reload();
            },
            error: function (xhr) {
                console.log("Error deleting course", xhr.responseText);
            }
        });
    }
});