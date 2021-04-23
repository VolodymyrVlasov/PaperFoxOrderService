interface Order {
    timeStamp: Date
    customer: Customer
    deliveryMethodType: DeliveryMethodType
    paymentMethodType: PaymentMethodType
    orderStatusType?: OrderStatusType
    orderID?: string
}

interface CalculationParams {
    materialType: MaterialType
    cuttingType: CuttingType
}

interface Customer {
    firstName: string
    lastName?: string | null
    phoneNumber?: string
    email?: string | null
}

interface Size {
    diameter?: number
    width?: number
    height?: number
    borderRadius?: number
}

interface Material {
    type: MaterialType;
    index: number;
    name: string;
}

interface PrintingProduct {
    quantity: number,
    materialType: MaterialType, // todo: Material[]
    size: Size | null,
    cuttingType: CuttingType
    totalPrice?: number
    productType: ProductType
}


enum PaymentMethodType {
    LIQ_PAY = "LIQ_PAY",
    CASH = "CASH",
    IBAN = "IBAN",
    CARD = "CARD"
}

enum ProductType {
    STICKER = "STICKER",
    BIZ_CARD = "BIZ_CARD",
    FLYER = "FLYER",
    POSTER = "POSTER",
    DRAWING = "DRAWING",
    PHOTO = "PHOTO",
    MAGNET = "MAGNET"
}

enum OrderStatusType {
    NEW = "NEW",
    IN_PROGRESS = "IN_PROGRESS",
    NEED_CHANGE = "NEED_CHANGE",
    COMPLETED = "COMPLETED",
    CANCELED = "CANCELED"
}

enum DeliveryMethodType {
    PICK_UP = "PICK_UP",
    NOVA_POSHTA = "NOVA_POSHTA",
    UKLON = "UKLON"
}

enum CuttingType {
    HAND_CUTTING = "HAND_CUTTING",
    PLOTTER_CUTTING = "PLOTTER_CUTTING",
    THROUGH_PLOTTER_CUTTING = "THROUGH_PLOTTER_CUTTING"
}

enum MaterialType {
    RAFLATAC = "RAFLATAC",
    RAFLATAC_LAMINATED = "RAFLATAC_LAMINATED",
    RAFLATAC_PET = "RAFLATAC_PET",
    RITRAMA_LAMINATED = "RITRAMA_LAMINATED",
    RITRAMA_TRANSPARENT = "RITRAMA_TRANSPARENT",
}
