/** 카테고리 선택 시 이벤트 처리 */
const subCateForMainCate1 = '\
<option value="">- 서브카테고리 선택 -</option>\
<option value="1">메인카테1-서브카테1</option>\
<option value="2">메인카테1-서브카테2</option>\
<option value="3">메인카테1-서브카테3</option>\
'

const subCateForMainCate2 = '\
<option value="">- 서브카테고리 선택 -</option>\
<option value="4">메인카테2-서브카테4</option>\
<option value="5">메인카테2-서브카테5</option>\
'

$('#maincate').on('change', function (e) {
    if ($(this).val() == '') {
        console.log('maincate 아무것도 선택 안 됨')
        $('#subcate').html('<option value="">- 서브카테고리 선택 -</option>')
    } else if ($(this).val() == '1') {
        console.log('maincate 1번 선택')
        $('#subcate').html(subCateForMainCate1)
    } else if ($(this).val() == '2') {
        console.log('maincate 2번 선택')
        $('#subcate').html(subCateForMainCate2)
    }
})
/** keyword를 입력하고 enter 입력 시 이벤트 처리 */
$('#keyword-input').on('keyup', function (e) {
    if (!((e.keyCode === 13) && e.target.value)) {
        return
    }

    if ($('#keyword-wrap').children().length === 10) {
        alert('키워드는 총 10개까지 입력할 수 있습니다')
        e.target.value = ''
        return
    }

    const inputKeyword = e.target.value
    console.log('inputKeyword: ' + inputKeyword)

    const keywordDiv = document.createElement('div')
    keywordDiv.classList.add('keyword')

    const spanTag = document.createElement('span')
    spanTag.innerText = inputKeyword

    const buttonTag = document.createElement('button')
    buttonTag.classList.add('delete')

    buttonTag.onclick = function () {
        keywordDiv.remove()
    }

    keywordDiv.appendChild(spanTag)
    keywordDiv.appendChild(buttonTag)
    $('#keyword-wrap').append(keywordDiv)
    e.target.value = ''
})
/** 비디오 업로드를 위한 Drag & Drop event handler */

const VIDEO_MAX_FILES = 1;
const videoFileUploadWrapperTag = document.getElementById('video-file-upload-wrapper');
const videoFileList = document.getElementById('video-file-list');
const videoMap = new Map();

videoFileUploadWrapperTag.addEventListener('dragover', function (e) {
    e.preventDefault();
});

videoFileUploadWrapperTag.addEventListener('drop', function (e) {
    console.log('Video drop!');
    e.preventDefault();

    const videoFileList = e.dataTransfer.files;
    handleVideoFiles(videoFileList);
});

function handleVideoFiles(video) {
    if (videoFileList.childElementCount + video.length > VIDEO_MAX_FILES) {
        alert('최대 ' + VIDEO_MAX_FILES + '개까지 업로드할 수 있습니다.');
        return;
    }

    const existingFiles = Array.from(videoFileList.children).map(
        (item) => item.dataset.fileName
    );

    for (const file of video) {
        if (!file.type.match('video/mp4')) {
            alert('비디오 파일(mp4 형식)만 등록할 수 있습니다');
            continue;
        }

        if (existingFiles.includes(file.name)) {
            alert('동일한 파일은 중복해서 업로드할 수 없습니다: ' + file.name);
            continue;
        }

        const videoURL = URL.createObjectURL(file);

        const videoFileName = file.name;

        let videoFileItem = document.createElement('li');
        videoFileItem.className = 'fileItem';
        videoFileItem.dataset.fileName = videoFileName;

        // 비디오 미리보기
        let video = document.createElement('video');
        video.src = videoURL;
        video.title = videoFileName;
        video.classList.add('thumb-video');
        video.style.width = '400px';
        video.style.height = '400px';

        // 삭제 버튼 추가
        let deleteButton = createControlButton('X', 'deleteButton');
        deleteButton.addEventListener('click', function () {
            videoFileItem.remove();
            videoMap.delete(videoFileName);
        });

        // 컨트롤 버튼을 contentsFileItem에 추가
        videoFileItem.appendChild(video);
        videoFileItem.appendChild(deleteButton);
        videoFileList.appendChild(videoFileItem);

        videoMap.set(videoFileName, file);
    }
}

function createControlButton(text, className) {
    let button = document.createElement('div');
    button.className = className;
    button.innerHTML = text;
    return button;
}



function handleVideoUpload(event) {
    const file = event.target.files[0];
    const fileList = new FileList();
    fileList.append(file);

    handleContentsFiles(fileList);
}


/** 썸네일 사진(들을) 업로드를 위한 Drag & Drop event handler */

const thumbnailMap = new Map();
const THUMBNAIL_MAX_FILES = 10
const thumbnailFileUploadWrapperTag = document.getElementById('thumbnail-file-upload-wrapper')
const thumbnailFileList = document.getElementById('thumbnail-file-list')

thumbnailFileUploadWrapperTag.addEventListener('dragover', function (e) {
    e.preventDefault()
})

thumbnailFileUploadWrapperTag.addEventListener('drop', function (e) {
    console.log('thumbnail drop!')
    e.preventDefault()

    const thumbnailFileList = e.dataTransfer.files
    handleThumbnailFiles(thumbnailFileList)
})

function handleThumbnailFiles(thumbnails) {
    if (thumbnailFileList.childElementCount + thumbnails.length > THUMBNAIL_MAX_FILES) {
        alert('최대 ' + THUMBNAIL_MAX_FILES + '개까지 업로드할 수 있습니다.')
        return;
    }

    const existingFiles = Array.from(thumbnailFileList.children)
        .map(
            (item) => {
                console.log(item.dataset.fileName)
                return item.dataset.fileName
            }
        )

    for (const file of thumbnails) {
        console.log(file)
        if (!file.type.match('image.*')) {
            alert('사진만 등록할 수 있습니다')
            continue
        }

        if (existingFiles.includes(file.name)) {
            alert('동일한 파일은 중복해서 업로드할 수 없습니다: ' + file.name)
            continue
        }

        let reader = new FileReader()

        reader.onload = function (e) {
            const thumbnailFileName = file.name

            let thumbnailFileItem = document.createElement('li')
            thumbnailFileItem.className = 'fileItem'
            thumbnailFileItem.dataset.fileName = thumbnailFileName

            let thumbnailDeleteButton = document.createElement('span')
            thumbnailDeleteButton.className = 'deleteButton'
            thumbnailDeleteButton.innerHTML = 'X'
            thumbnailDeleteButton.onclick = function () {
                thumbnailFileItem.remove()
                thumbnailMap.delete(thumbnailFileName)
            }

            let thumbnailImage = document.createElement('img')
            thumbnailImage.src = e.target.result
            thumbnailImage.title = thumbnailFileName
            thumbnailImage.classList.add('thumb-img')
            thumbnailFileItem.appendChild(thumbnailImage)

            thumbnailFileItem.appendChild(thumbnailDeleteButton)
            thumbnailFileList.appendChild(thumbnailFileItem)

            thumbnailMap.set(thumbnailFileName, file)
        }
        reader.readAsDataURL(file)
    }
}

$(function () {
    $('#thumbnail-file-list').sortable()
})

/** 저장 버튼을 눌렀을 때 event handle */

function handleVideoInformationWhenSaveButtonClicked(thumbnailFileInfoList, videoInfo) {
    let keywordsWithCommas = ""

    function handleKeyword() {
        $('.keyword > span').each(function (index, item) {
            keywordsWithCommas = keywordsWithCommas + $(item).text() + ","
        })
    }

    handleKeyword()

    const videoSaveRequestObj = {
        type: $('#type option:selected').val(),
        display: $('input:radio[name="display"]:checked').val(),
        name: $('#name').val(),
        keyword: keywordsWithCommas,
        explanation: $('#explanation').val(),
        source: $('#source').val(),
        subCategory: $('#subcate option:selected').val()
    }
    console.log(videoSaveRequestObj)

    videoSaveRequestObj.thumbnailsSaveRequestList = thumbnailFileInfoList
    videoSaveRequestObj.fileSaveRequestDTO = videoInfo

    $.ajax({
        type: 'post',
        url: '/video/save',
        data: JSON.stringify(videoSaveRequestObj),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (result) {
            console.log('video save success!')
            alert('비디오가 등록되었습니다.')
            console.log(result)
        },
        error: function (err) {
            console.log('video save error!')
            alert('비디오가 등록에 실패했습니다.')
            console.log(err)
        }
    })
}

function handleThumbnailImagesWhenSaveButtonClicked() {
    // 썸네일 사진
    let thumbnailOrder = 1;
    const thumbnailFileInfoList = Array.from(thumbnailFileList.children)
        .map((thumbnail) => {
            return {
                orders: thumbnailOrder++,
                original: thumbnail.dataset.fileName,
                //thumbnailFileData: thumbnailMap.get(thumbnail.dataset.fileName)
            }
        })
    console.log(thumbnailFileInfoList)

    thumbnailFileInfoList.forEach((thumbnail) => {
        const presignedUploadRequestObj = {
            path: 'video/thumbnails/',
            originalFileName: thumbnail.original
        }
        $.ajax({
            type: 'post',
            url: '/presigned/upload-url',
            data: JSON.stringify(presignedUploadRequestObj),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (result) {
                // console.log('typeof: ' + typeof(result)) -> object
                thumbnail.presignedUrl = result.presignedUrl
                thumbnail.path = result.path
                thumbnail.saved = result.fileNameWithUUID
            },
            error: function (err) {
                console.log('ajax error!')
                console.log(err)
            }
        })
    })
    console.log(thumbnailFileInfoList)

    thumbnailFileInfoList.forEach((thumbnail) => {
        $.ajax({
            type: 'put',
            url: thumbnail.presignedUrl,
            data: thumbnailMap.get(thumbnail.original),
            processData: false,
            contentType: 'binary/octet-stream',
            async: false,
            success: function (result) {
                console.log('thumbnail s3 upload success: ' + thumbnail.orders)
                console.log(result)
            },
            error: function (err) {
                console.log('thumbnail s3 upload error: ' + thumbnail.orders)
                console.log(err)
            }
        })
    })

    return thumbnailFileInfoList
}

function handleVideo() {
    const videoFileName = $('#video-file-list > li').data('file-name')

    const videoInfo = {
        original: videoFileName
    }

    $.ajax({
        type: 'post',
        url: '/presigned/upload-url',
        data: JSON.stringify({
            path: 'video/',
            originalFileName: videoFileName
        }),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        // 비동기 - > 값을 서버에 요청 - 값을 받는걸 기다리지 않고 바로 다음 줄 실행
        async: false,
        success: function (result) {
            console.log('video upload presigned url generation success')
            console.log(result)
            videoInfo.saved = result.fileNameWithUUID
            videoInfo.presignedUrl = result.presignedUrl
        },
        error: function (err) {
            console.log('video upload presigned url generation error')
            console.log(err)
        }
    })

    $.ajax({
        type: 'put',
        url: videoInfo.presignedUrl,
        data: videoMap.get(videoInfo.original),
        processData: false,
        contentType: 'binary/octet-stream',
        async: false,
        success: function (result) {
            console.log(result)
        },
        error: function (err) {
            console.log(err)
        }
    })
    return videoInfo
}

function validationCheck() {
    const name = $('#name').val()
    const keywordDivTagLength = $('#keyword-wrap > .keyword').length
    const thumbnailLength = $('#thumbnail-file-list > .fileItem').length
    const videoLength = $('video-file-list > .fileItem').length
    const subCate = $('#subcate option:selected').val()

    if (!name) {
        alert('비디오 제목을 입력해주세요')
        return false
    }
    if (!keywordDivTagLength) {
        alert('키워드를 한 개 이상 작성해주세요')
        return false
    }
    if (!subCate) {
        alert('카테고리를 선택해주세요')
        return false
    }
    if (!videoLength) {
        alert('비디오(.mp4형식)를 등록해주세요')
        return false
    }
    if (!thumbnailLength) {
        alert('썸네일 사진을 한 개 이상 등록해주세요')
        return false
    }

    return true
}


$('#save-btn').on('click', function (e) {
    console.log('save-btn click!')
    if (!validationCheck()) {
        return
    }
    const thumbnailFileInfoList = handleThumbnailImagesWhenSaveButtonClicked()
    const videoInfo = handleVideo()
    console.log(videoInfo)
    handleVideoInformationWhenSaveButtonClicked(thumbnailFileInfoList, videoInfo)
}) // save-btn on click handler ends

/** radio button */

$('input[type="radio"]').on('click', function () {
    let _this = $(this);
    if (_this.hasClass('radio02_2')) {
        _this.parent('td').find('.input-text-wrap').css('display', 'inline-block');
    } else if (_this.hasClass('radio02_1')) {
        _this.parent('td').find('.input-text-wrap').css('display', 'none');
    }
})