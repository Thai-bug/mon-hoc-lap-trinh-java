const ctx = document.getElementById('myChart');
const myChart = new Chart(ctx, {
        type: 'pie',
        data: {
            title: '',
            datasets: [{}]
        },
        options: {
            maintainAspectRatio: false,
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: 'Đơn hàng theo loại',
                    padding: {
                        top: 10,
                        bottom: 30
                    }
                }
            }
        }
    }
);

let label = [];
let data = [];
let color = [];


window.exportType = function exportType() {
    let sheet = [];
    let sheet_1_data = [{Col_One: 1, Col_Two: 11}, {Col_One: 2, Col_Two: 22}];
    for (let i = 0; i < label.length; i++) {
        sheet.push({
            "Loại tiệc": label[i],
            "Số lượng": data[i]
        });
    }

    console.log(sheet)

    let opts = [{sheetid: 'Sheet One', header: true}];
    let result = alasql('SELECT * INTO XLSX("bao-cao-don-hang-theo-loai.xlsx",?) FROM ?',
        [opts, [sheet]]);
}


$(async function () {
    const response = await axios.get('api/v1/admin/bills/total-by-type').catch(e => e);
    if (response instanceof Error)
        return;

    response.data.forEach(item => {
        label.push(item[5])
        data.push(item[0])
        color.push(item[6])
    })

    myChart.data.labels = label;
    myChart.data.datasets[0].backgroundColor = color
    myChart.data.datasets[0].data = data;
    myChart.update();
})

