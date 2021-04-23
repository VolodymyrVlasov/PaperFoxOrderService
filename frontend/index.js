"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var _a, _b, _c;
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
    static calcRoundSticker(sticker) {
        return __awaiter(this, void 0, void 0, function* () {
            // console.log(sticker)
            const url = new URL("http://62.244.50.147:8080/api/calc");
            let request = new Request(url.toString(), {
                method: "POST",
                body: JSON.stringify(sticker),
                headers: {
                    "Content-Type": "application/json"
                }
            });
            yield fetch(request)
                .then(response => response.json())
                .then(response => console.log(response))
                .catch(error => console.log(error));
        });
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
var MaterialType;
(function (MaterialType) {
    MaterialType["RAFLATAC"] = "RAFLATAC";
    MaterialType["RAFLATAC_LAMINATED"] = "RAFLATAC_LAMINATED";
    MaterialType["RAFLATAC_PET"] = "RAFLATAC_PET";
    MaterialType["RITRAMA_LAMINATED"] = "RITRAMA_LAMINATED";
    MaterialType["RITRAMA_TRANSPARENT"] = "RITRAMA_TRANSPARENT";
})(MaterialType || (MaterialType = {}));
var PaymentMethodType;
(function (PaymentMethodType) {
    PaymentMethodType["LIQ_PAY"] = "LIQ_PAY";
    PaymentMethodType["CASH"] = "CASH";
    PaymentMethodType["IBAN"] = "IBAN";
    PaymentMethodType["CARD"] = "CARD";
})(PaymentMethodType || (PaymentMethodType = {}));
var OrderStatusType;
(function (OrderStatusType) {
    OrderStatusType["NEW"] = "NEW";
    OrderStatusType["IN_PROGRESS"] = "IN_PROGRESS";
    OrderStatusType["NEED_CHANGE"] = "NEED_CHANGE";
    OrderStatusType["COMPLETED"] = "COMPLETED";
    OrderStatusType["CANCELED"] = "CANCELED";
})(OrderStatusType || (OrderStatusType = {}));
var DeliveryMethodType;
(function (DeliveryMethodType) {
    DeliveryMethodType["PICK_UP"] = "PICK_UP";
    DeliveryMethodType["NOVA_POSHTA"] = "NOVA_POSHTA";
    DeliveryMethodType["UKLON"] = "UKLON";
})(DeliveryMethodType || (DeliveryMethodType = {}));
var CuttingType;
(function (CuttingType) {
    CuttingType["HAND_CUTTING"] = "HAND_CUTTING";
    CuttingType["PLOTTER_CUTTING"] = "PLOTTER_CUTTING";
    CuttingType["THROUGH_PLOTTER_CUTTING"] = "THROUGH_PLOTTER_CUTTING";
})(CuttingType || (CuttingType = {}));
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
        // orderID: null,
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
(_c = document.getElementById('calc_round_sticker')) === null || _c === void 0 ? void 0 : _c.addEventListener('click', function () {
    let roundSticker;
    roundSticker = {
        quantity: 100,
        materialType: MaterialType.RAFLATAC,
        cuttingType: CuttingType.PLOTTER_CUTTING,
        size: {
            diameter: 50,
            width: 0,
            height: 0,
            borderRadius: 0
        }
    };
    TestOrder.calcRoundSticker(roundSticker);
});
