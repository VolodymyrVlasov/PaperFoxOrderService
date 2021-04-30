import {Customer} from "./Customer.js";
import {DeliveryMethodType} from "./DeliveryMethodType.js";
import {PaymentMethodType} from "./PaymentMethodType.js";
import {OrderStatusType} from "./OrderStatusType.js";


export interface Order {
    timeStamp: Date
    customer: Customer
    deliveryMethodType: DeliveryMethodType
    paymentMethodType: PaymentMethodType
    orderStatusType?: OrderStatusType
    orderID?: string
}
