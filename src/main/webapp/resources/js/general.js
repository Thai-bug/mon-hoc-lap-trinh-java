const typeChartField = document.getElementById('type-chart');
const typeChart = new Chart(typeChartField, {
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

const staticChartField = document.getElementById('static-chart');
const staticChart = new Chart(staticChartField, {
        type: 'line',
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
                    text: 'Đơn hàng theo thời gian',
                    padding: {
                        top: 10,
                        bottom: 30
                    }
                }
            }
        }
    }
);


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

async function billByTypes(){
    let label = [];
    let data = [];
    let color = [];
    const response = await axios.get('api/v1/admin/bills/total-by-type').catch(e => e);
    if (response instanceof Error)
        return;
    response.data.forEach(item => {
        label.push(item[5])
        data.push(item[0])
        color.push(item[6])
    })

    typeChart.data.labels = label;
    typeChart.data.datasets[0].backgroundColor = color
    typeChart.data.datasets[0].data = data;
    typeChart.update();
}

async function staticBill(type){
    let label = [];
    let data = [];
    const response = await axios.post('api/v1/admin/bills/static',{
        type: type
    }).catch(e => e);
    if (response instanceof Error)
        return;
    response.data.forEach(item => {
        data.push(item.total)
        switch (type){
            case "month":
                label.push(`${item.month.toString().padStart(2, '0')}\\${item.year}`);
                break;
            case "quarter":
                label.push(`Quý ${item.quarter} năm ${item.year}`);
                break;
            default:
                label.push(moment(item.createdAt,'x').format('DD/MM/YYYY'));
                break;
        }
        // color.push(item[6])
    })

    staticChart.data.labels = label;
    staticChart.data.datasets[0].borderColor = 'rgb(75, 192, 192)'
    staticChart.data.datasets[0].data = data;
    staticChart.data.datasets[0].label = 'Thống kê đơn hàng theo ngày';
    staticChart.update();
}

$(async function () {
    await billByTypes();
    await staticBill('date');
})

$('#static-bill-time').on('change', async function(e){
    await staticBill($(this).val());
})
