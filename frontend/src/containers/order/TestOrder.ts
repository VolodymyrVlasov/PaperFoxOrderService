import {Order} from "../../types/Order.js";

export class TestOrder{
    public  createDefaultOrder(order: Order) {
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
}