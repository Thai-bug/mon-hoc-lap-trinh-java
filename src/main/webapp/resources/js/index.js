/**
 * GLOBAL REGION
 * */

//document is ready to use
$( document ).ready(function() {
    preprocessToggle();
})

File.prototype.convertToBase64 = function (callback) {
    let reader = new FileReader();
    reader.onloadend = function (e) {
        callback(e.target.result, e.target.error);
    };
    reader.readAsDataURL(this);
};

//preprocess toggle
function preprocessToggle(){
    let toggle = $('.status-select')
    let value = toggle.find(':selected').val()
    if(value === 'true') {
        toggle.addClass('select-success');
        $('.toggle-active-status-title').removeClass('hidden');
        return
    }
    toggle.addClass('select-error ');
    $('.toggle-disabled-status-title').removeClass('hidden');
}

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
 * PROCESS SELECT STATUS
 * */
$('.status-select').on('change', function(e){
    let toggle = $(this)
    let value = toggle.find(':selected').val()
    if(value === 'true') {
        toggle.attr('value','false')
        $('.toggle-active-status-title').removeClass('hidden');
        $('.toggle-disabled-status-title').addClass('hidden');

        toggle.addClass('select-success');
        toggle.removeClass('select-error');
        return
    }
    $('.toggle-active-status-title').addClass('hidden');
    $('.toggle-disabled-status-title').removeClass('hidden');
    toggle.addClass('select-error');
    toggle.removeClass('select-success');
})

/**
 * END REGION
 * */

/**
 * end region
 * */

const btn = document.querySelector("button.mobile-menu-button");
const menu = document.querySelector(".mobile-menu");

// add event listeners
btn.addEventListener("click", () => {
    menu.classList.toggle("hidden");
});


