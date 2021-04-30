import {TestOrder} from "./TestOrder.js";
import {DeliveryMethodType, OrderStatusType, PaymentMethodType, Order} from "../../types/index.js";

document.getElementById("create_order_btn")?.addEventListener('click', function (e) {
    e.preventDefault()
    const name = (<HTMLInputElement>document.getElementById("first_name"))?.value
    const last_name = (<HTMLInputElement>document.getElementById("last_name"))?.value
    const phone = (<HTMLInputElement>document.getElementById("phone"))?.value
    const email = (<HTMLInputElement>document.getElementById("email"))?.value
    const delivery = (<HTMLInputElement>document.getElementById("delivery_method"))?.value
    const payment = (<HTMLInputElement>document.getElementById("payment_method"))?.value

    let order: Order = {
        customer: {
            firstName: name,
            lastName: last_name,
            phoneNumber: phone,
            email: email,
        },
        timeStamp: new Date,
        orderStatusType: OrderStatusType.NEW,
        deliveryMethodType: DeliveryMethodType[delivery as keyof typeof DeliveryMethodType],
        paymentMethodType: PaymentMethodType[payment as keyof typeof PaymentMethodType],
    }

    new TestOrder().createDefaultOrder(order)
})

