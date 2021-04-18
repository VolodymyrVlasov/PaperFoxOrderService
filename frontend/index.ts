class TestOrder {
    public static createDefaultOrder(order: Order) {

        let url: URL = new URL("http://127.0.0.1:8080/api/order")

        let request: Request = new Request(
            url.toString(),
            {
                method: "POST",
                body: JSON.stringify(order),
                mode: "no-cors"
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

interface Order {
    name: string | null
    lastName: string | null
    phone: string | null
    email: string | null
    deliveryMethodType: DeliveryMethodType
    paymentMethodType: PaymentMethodType
    file: File | null
}

document.getElementById("create_order_btn")?.addEventListener('click', function (e) {
    e.preventDefault()
    const name = (<HTMLInputElement>document.getElementById("first_name"))?.nodeValue
    const last_name = (<HTMLInputElement>document.getElementById("last_name"))?.nodeValue
    const phone = (<HTMLInputElement>document.getElementById("phone"))?.nodeValue
    const email = (<HTMLInputElement>document.getElementById("email"))?.nodeValue
    const delivery = (<HTMLInputElement>document.getElementById("delivery_method"))?.nodeValue
    const payment = (<HTMLInputElement>document.getElementById("payment_method"))?.nodeValue

    let files = (<HTMLInputElement>document.getElementById("file"))?.files

    if (files == null) return

    let order: Order = {
        name: name,
        lastName: last_name,
        phone: phone,
        email: email,
        deliveryMethodType: DeliveryMethodType[delivery as keyof typeof DeliveryMethodType],
        paymentMethodType: PaymentMethodType[payment as keyof typeof PaymentMethodType],
        file: files.item(0)
    }



    TestOrder.createDefaultOrder(order)
})



