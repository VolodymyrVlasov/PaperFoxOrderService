"use strict";
var _a, _b;
class TestOrder {
    static createDefaultOrder(order) {
        // console.log(order)
        let url = new URL("http://62.244.50.147:8080/api/order");
        let request = new Request(url.toString(), {
            method: "POST",
            body: JSON.stringify(order),
            headers: {
                "Content-Type": "application/json"
            }
        });
        fetch(request)
            .then(response => console.log(response.status))
            .catch(error => console.log(error));
    }
    static getOrder() {
        let url = new URL("http://62.244.50.147:8080/api/order"); // "http://62.244.50.147/api/order"
        let request = new Request(url.toString(), {
            method: "GET",
        });
        fetch(request)
            .then(response => response.json())
            .then(response => this.renderOrderList(response))
            .catch(error => console.log(error));
    }
    static renderOrderList(orderList) {
        let rootList = document.getElementById('order_cnt');
        orderList.forEach((order) => {
            if (rootList != null) {
                rootList.innerHTML += `
                      <div class="order_row">
                        <span>${new Date(order.timeStamp).getDay()}</span>
         
                        <span>${order.customer.firstName} ${order.customer.lastName}</span>
                        <span>${order.customer.phoneNumber}</span>
                        <span>${order.customer.email}</span>
                        <span>${order.deliveryMethodType}</span>
                        <span>${order.paymentMethodType}</span>
                        <span>${order.orderStatusType}</span>
                    </div>
            `;
            }
        });
    }
}
(_a = document.getElementById("create_order_btn")) === null || _a === void 0 ? void 0 : _a.addEventListener('click', function (e) {
    var _a, _b, _c, _d, _e, _f;
    e.preventDefault();
    const name = (_a = document.getElementById("first_name")) === null || _a === void 0 ? void 0 : _a.value;
    const last_name = (_b = document.getElementById("last_name")) === null || _b === void 0 ? void 0 : _b.value;
    const phone = (_c = document.getElementById("phone")) === null || _c === void 0 ? void 0 : _c.value;
    const email = (_d = document.getElementById("email")) === null || _d === void 0 ? void 0 : _d.value;
    const delivery = (_e = document.getElementById("delivery_method")) === null || _e === void 0 ? void 0 : _e.value;
    const payment = (_f = document.getElementById("payment_method")) === null || _f === void 0 ? void 0 : _f.value;
    let order = {
        customer: {
            firstName: name,
            lastName: last_name,
            phoneNumber: phone,
            email: email,
        },
        timeStamp: new Date,
        orderStatusType: OrderStatusType.NEW,
        deliveryMethodType: DeliveryMethodType[delivery],
        paymentMethodType: PaymentMethodType[payment],
    };
    TestOrder.createDefaultOrder(order);
});
(_b = document.getElementById('get_order_btn')) === null || _b === void 0 ? void 0 : _b.addEventListener('click', function (e) {
    e.preventDefault();
    TestOrder.getOrder();
});
