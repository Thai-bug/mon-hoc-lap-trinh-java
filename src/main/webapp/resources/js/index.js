/**
 * GLOBAL REGION
 * */

File.prototype.convertToBase64 = function (callback) {
    let reader = new FileReader();
    reader.onloadend = function (e) {
        callback(e.target.result, e.target.error);
    };
    reader.readAsDataURL(this);
};

/**
 * END REGION
 * */

/**
 * update avatar region
 *
 * */

$('#change-avatar').on("click", function () {
    $('#avatar-user').addClass("hidden")
    $('#avatar-upload').removeClass("hidden")
})

$('#cancel-avatar').on("click", function () {
    $('#avatar-user').removeClass("hidden")
    $('#avatar-upload').addClass("hidden")
})

$('#avatar').on('change', function (e) {
    let selectedFile = this.files[0];
    selectedFile.convertToBase64(function (base64) {
        $('#show-avatar').attr("src",base64);
        $('#update-avatar').attr("disabled",false);
    })
})

/**
 * end region
 * */

const btn = document.querySelector("button.mobile-menu-button");
const menu = document.querySelector(".mobile-menu");

// add event listeners
btn.addEventListener("click", () => {
    menu.classList.toggle("hidden");
});


