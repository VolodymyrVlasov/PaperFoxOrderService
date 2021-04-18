"use strict";
var _a;
class TestOrder {
    static createDefaultOrder(order) {
        let url = new URL("http://127.0.0.1:8080/api/order");
        let request = new Request(url.toString(), {
            method: "POST",
            body: JSON.stringify(order),
            mode: "no-cors"
        });
        fetch(request)
            .then(resolve => {
            console.log(resolve.status);
        })
            .catch(error => {
            console.log(error);
        });
    }
}
var PaymentMethodType;
(function (PaymentMethodType) {
    PaymentMethodType["LIQ_PAY"] = "LIQ_PAY";
    PaymentMethodType["CASH"] = "CASH";
    PaymentMethodType["IBAN"] = "IBAN";
    PaymentMethodType["CARD"] = "CARD";
})(PaymentMethodType || (PaymentMethodType = {}));
var DeliveryMethodType;
(function (DeliveryMethodType) {
    DeliveryMethodType["PICK_UP"] = "PICK_UP";
    DeliveryMethodType["NOVA_POSHTA"] = "NOVA_POSHTA";
    DeliveryMethodType["UKLON"] = "UKLON";
})(DeliveryMethodType || (DeliveryMethodType = {}));
(_a = document.getElementById("create_order_btn")) === null || _a === void 0 ? void 0 : _a.addEventListener('click', function (e) {
    var _a, _b, _c, _d, _e, _f, _g;
    e.preventDefault();
    const name = (_a = document.getElementById("first_name")) === null || _a === void 0 ? void 0 : _a.nodeValue;
    const last_name = (_b = document.getElementById("last_name")) === null || _b === void 0 ? void 0 : _b.nodeValue;
    const phone = (_c = document.getElementById("phone")) === null || _c === void 0 ? void 0 : _c.nodeValue;
    const email = (_d = document.getElementById("email")) === null || _d === void 0 ? void 0 : _d.nodeValue;
    const delivery = (_e = document.getElementById("delivery_method")) === null || _e === void 0 ? void 0 : _e.nodeValue;
    const payment = (_f = document.getElementById("payment_method")) === null || _f === void 0 ? void 0 : _f.nodeValue;
    let files = (_g = document.getElementById("file")) === null || _g === void 0 ? void 0 : _g.files;
    if (files == null)
        return;
    let order = {
        name: name,
        lastName: last_name,
        phone: phone,
        email: email,
        deliveryMethodType: DeliveryMethodType[delivery],
        paymentMethodType: PaymentMethodType[payment],
        file: files.item(0)
    };
    TestOrder.createDefaultOrder(order);
});
