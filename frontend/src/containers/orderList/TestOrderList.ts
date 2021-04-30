import {Order} from "../../types/index.js";

export class TestOrderList{
    public  getOrder() {
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

    public renderOrderList(orderList: Array<Order>) {
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