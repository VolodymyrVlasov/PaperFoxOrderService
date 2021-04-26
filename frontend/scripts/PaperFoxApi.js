"use strict";
var PaymentMethodType;
(function (PaymentMethodType) {
    PaymentMethodType["LIQ_PAY"] = "LIQ_PAY";
    PaymentMethodType["CASH"] = "CASH";
    PaymentMethodType["IBAN"] = "IBAN";
    PaymentMethodType["CARD"] = "CARD";
})(PaymentMethodType || (PaymentMethodType = {}));
var ProductType;
(function (ProductType) {
    ProductType["STICKER"] = "STICKER";
    ProductType["BIZ_CARD"] = "BIZ_CARD";
    ProductType["FLYER"] = "FLYER";
    ProductType["POSTER"] = "POSTER";
    ProductType["DRAWING"] = "DRAWING";
    ProductType["PHOTO"] = "PHOTO";
    ProductType["MAGNET"] = "MAGNET";
})(ProductType || (ProductType = {}));
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
var MaterialType;
(function (MaterialType) {
    MaterialType["RAFLATAC"] = "RAFLATAC";
    MaterialType["RAFLATAC_LAMINATED"] = "RAFLATAC_LAMINATED";
    MaterialType["RAFLATAC_PET"] = "RAFLATAC_PET";
    MaterialType["RITRAMA_LAMINATED"] = "RITRAMA_LAMINATED";
    MaterialType["RITRAMA_TRANSPARENT"] = "RITRAMA_TRANSPARENT";
})(MaterialType || (MaterialType = {}));
var MaterialGroupType;
(function (MaterialGroupType) {
    MaterialGroupType["SELF_ADHESIVE"] = "SELF_ADHESIVE";
})(MaterialGroupType || (MaterialGroupType = {}));
