const getInitialParams = 'http://62.244.50.147:8080/api/stickers/getInitialParams?type=round'

let calculatorParams = []


fetch(getInitialParams)
    .then(response => response.json())
    .then(response => renderOptions(response))
    // .then(response => console.log(response))

    .catch(error => console.log(error))

function renderOptions(response: CalculationParams) {
    console.log(response.materialType)
    console.log(response.cuttingType)

    const materialType = response.materialType

    console.log(typeof materialType)

    // for (let key of Object.keys(materialType)) {
    //     let mealName = materialType[key];
    //     // ... do something with mealName
    //     console.log(mealName);
    // }
    let cuttingTypeSelect = document.getElementById('sticker_material')


    Object.keys(materialType).forEach((e) => {
        // let option = document.createElement('option')
        console.log(e, e.toString())
        // option.innerText = e

        // cuttingTypeSelect.appendChild(option)
    })
}

interface CalculationParams {
    materialType: MaterialType
    cuttingType: CuttingType
}