import {TestOrderList} from "./TestOrderList.js";

document.getElementById('get_order_btn')?.addEventListener('click', function (e) {
    e.preventDefault()
    new TestOrderList().getOrder()
})
