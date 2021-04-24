class TestOrder {
    public static createDefaultOrder(order: Order) {
        // console.log(order)
        let url: URL = new URL("http://62.244.50.147:8080/api/order")
        let request: Request = new Request(
            url.toString(),
            {
                method: "POST",
                body: JSON.stringify(order),
                headers: {
                    "Content-Type": "application/json"
                }
            }
        )

        fetch(request)
            .then(response => console.log(response.status))
            .catch(error => console.log(error))
    }

    public static getOrder() {
        let url: URL = new URL("http://62.244.50.147:8080/api/order") // "http://62.244.50.147/api/order"
        let request: Request = new Request(
            url.toString(),
            {
                method: "GET",
            }
        )

        fetch(request)
            .then(response => response.json())
            .then(response => this.renderOrderList(response))
            .catch(error => console.log(error))
    }

    static renderOrderList(orderList: Array<Order>) {
        let rootList = document.getElementById('order_cnt')

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
            `
            }
        })
    }
}

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

    TestOrder.createDefaultOrder(order)
})

document.getElementById('get_order_btn')?.addEventListener('click', function (e) {
    e.preventDefault()
    TestOrder.getOrder()
})

