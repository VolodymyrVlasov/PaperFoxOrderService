class TestOrder {
    public static createDefaultOrder(order: Order) {

        console.log(JSON.stringify(order))
        let url: URL = new URL("http://127.0.0.1:8080/api/order")

        let request: Request = new Request(
            url.toString(),
            {
                method: "POST",
                body: JSON.stringify(order),
                // mode: "no-cors",
                headers: {
                    "Content-Type": "application/json"
                }
            }
        )

        fetch(request)
            .then(resolve => {
                console.log(resolve.status)
            })
            .catch(error => {
                console.log(error)
            })
    }
}

enum PaymentMethodType {
    LIQ_PAY = "LIQ_PAY",
    CASH = "CASH",
    IBAN = "IBAN",
    CARD = "CARD"
}

enum DeliveryMethodType {
    PICK_UP = "PICK_UP",
    NOVA_POSHTA = "NOVA_POSHTA",
    UKLON = "UKLON"
}

interface Customer{
    firstName: string | null
    lastName: string | null
    phoneNumber: string | null
    email: string | null
}

interface Order {
    customer: Customer
    deliveryMethodType: DeliveryMethodType
    paymentMethodType: PaymentMethodType
    file: File | null
}

document.getElementById("create_order_btn")?.addEventListener('click', function (e) {
    e.preventDefault()
    const name = (<HTMLInputElement>document.getElementById("first_name"))?.value
    const last_name = (<HTMLInputElement>document.getElementById("last_name"))?.value
    const phone = (<HTMLInputElement>document.getElementById("phone"))?.value
    const email = (<HTMLInputElement>document.getElementById("email"))?.value
    const delivery = (<HTMLInputElement>document.getElementById("delivery_method"))?.value
    const payment = (<HTMLInputElement>document.getElementById("payment_method"))?.value

    let files = (<HTMLInputElement>document.getElementById("file"))?.files

    if (files == null) return

    let order: Order = {
        customer: {
            firstName: name,
            lastName: last_name,
            phoneNumber: phone,
            email: email,
        },
        deliveryMethodType: DeliveryMethodType[delivery as keyof typeof DeliveryMethodType],
        paymentMethodType: PaymentMethodType[payment as keyof typeof PaymentMethodType],
        file: files.item(0)
    }


    console.log(order)

    TestOrder.createDefaultOrder(order)
})



