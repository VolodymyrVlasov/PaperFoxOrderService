export const RoundStickerDOM = {
    part: `
<div class="sticker_root_cnt">
    <div class="sticker_input_cnt">
        <div class="label_cnt">
            <label class="sticker_label" for="sticker_size_input">
                <span class="count_label">Розмір: <span id="sticker_size_value">10</span> мм</span>
            </label>
            <input class="order_input_select" type="range" id="sticker_size_input" value="10" min="10" max="150"
            >
        </div>
        <div class="divider"></div>
        <div class="label_cnt">
            <label class="sticker_label" for="sticker_quantity_input">
                <span class="count_label">Кількість:<span id="sticker_quantity_value">713</span> шт</span>
            </label>
            <input class="order_input_select" type="range" id="sticker_quantity_input" value="1" min="1"
                   max="5000" step="1">
        </div>
    </div>

    <div class="sticker_select_cnt">
        <div class="label_cnt">
            <label class="sticker_label" for="sticker_material">
                <span class="count_label">Матеріал</span>
            </label>
            <select class="order_input_select" name="" id="sticker_material"></select>
        </div>
        <div class="divider"></div>
        <div class="label_cnt">
            <label for="sticker_cutting_type">
                <span class="count_label">Тип порізки</span>
            </label>
            <select class="order_input_select" name="" id="sticker_cutting_type"></select>
        </div>
    </div>
    <div class="sticker_preview_cnt">
        <div class="sticker_preview">
            <span class="sticker_preview_label" id="sticker_preview_size_label">10 мм</span>
            <div class="sticker_preview_arrow"></div>
        </div>
    </div>
</div>
<div class="calculator_result">
    <div>
        <div class="sticker_result">
            <span>Вартість: </span>
            <span id="sticker_result_price" style="font-weight: bold"></span>
            <span> грн</span>
        </div>
        <div class="sticker_result"><span>Орієнтовна дата готовності: </span>
            <span id="sticker_result_date" style="font-weight: bold"></span>
        </div>
    </div>
    <div>
        <input class="file_input" id="sticker_file" type="file">
    </div>
    <div>
        <buttton class="btn_add_to_card" id="calc_round_sticker">Додати у кошик</buttton>
    </div>
</div>
`
}

