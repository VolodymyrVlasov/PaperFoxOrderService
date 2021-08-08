import {DOMCalculatorResult} from "./DOMCalculatorResult.js";


export const DOMSquaredSticker = {
    part: `
        <div class="sticker_root_cnt">

          <div class="row">
              <div class="calculator_cnt ">

                        <div class="row">
                            <div class="input_select">
                              <div class="input_select_label"><label for="standard_size_input"></label>Оберіть стандартний розмір</div> 
                              <div class="input_select_select">
                                    <select name="" id="standard_size_input">
                                        <option>Обрано індивідуальний розмір</option>
                                        <option>A6 (148x105 мм)</option>
                                        <option>A5 (210x148 мм)</option>
                                        <option>A4 (297x210 мм)</option>
                                        <option>A3 (420x297 мм)</option>                                        
                                    </select>
                              </div> 
                            </div>
                        </div>
                        <div class="row">
                              <div class="input_range">
                                  <div class="input_range_label"><label class="sticker_label" for="input_size_width">Ширина</label></div>
                                  <div class="input_range_input"> <input type="range" step="1" min="15" max="148" id="input_size_width" value="90"/></div>
                              </div>
                              <div class="input_range">
                                  <div class="input_range_label"><label class="sticker_label" for="input_size_height">Довжина</label></div>
                                  <div class="input_range_input"><input type="range" step="1" min="15" max="148" id="input_size_height" value="50"/></div>
                              </div>
                              <div class="input_range">
                                <div class="input_range_label"><label  for="input_size_corner_radius">Радіус: <strong id="output_size_corner_radius">0</strong> мм</label></div> 
                                <div class="input_range_input"><input  type="range" id="input_size_corner_radius" value="0"/></div> 
                              </div>
                        </div>

                        <div class="row">
                              <div class="input_range">
                                    <div class="input_range_label"><label for="input_sticker_quantity">Кількість: <strong id="sticker_quantity_value">21</strong> шт</label></div> 
                                    <div class="input_range_input"><input type="range" id="sticker_quantity_input" value="21" min="21" max="2000" step="1"/></div> 
                              </div>
                        </div>


                        <div class="row">
                          <div class="input_select">
                              <div class="input_select_label"><label for="sticker_material">Матеріал</label></div> 
                              <div class="input_select_select">
                                  <select name="" id="sticker_material"></select>
                              </div> 
                          </div>

                          <div class="input_select">
                              <div class="input_select_label"><label for="sticker_cutting_type">Тип порізки</label></div> 
                              <div class="input_select_select">
                                  <select id="sticker_cutting_type"></select>
                              </div> 
                          </div>
                      </div>
                       <div class="row">
                          <div>
                            <input class="file_input" id="file" type="file" accept="image/jpeg, image/jpg, image/png, application/pdf" >
                          </div>
                       </div>
                 
                    
                </div>

                <div class="preview_cnt">
                  <div class="file_preview_cnt" id="file_preview_cnt">
                    <div class="file_preview_cnt_input_wraper">
        
                          <div class="file_preview_cnt_input_wraper_drop_area_design" id="drag_drop_area">
        
                                <div class="file_preview_cnt_input_wraper_drop_area_design_cut" id="file_preview_cnt_input_wraper_drop_area_design_cut">
        
                                    <div class="file_preview_cnt_input_wraper_drop_area_design_cut_width_size_cnt">
                                        <div class="horizontal_arrow">
                                            <div class="horizontal_arrow_left_arrow"></div>
                                            <div class="horizontal_arrow_line"></div>
                                            <div class="horizontal_arrow_text_field">
                                              <p id="output_size_preview_width">90<span> мм</span></p>
                                            </div>
                                            <div class="horizontal_arrow_line"></div>
                                            <div class="horizontal_arrow_right_arrow"></div>
                                        </div>
                                    </div>
                                    <div class="file_preview_cnt_input_wraper_drop_area_design_cut_height_size_cnt">
                                          <div class="vertical_arrow">
                                                <div class="vertical_arrow_top_arrow"></div>
                                                <div class="vertical_arrow_line"></div>
                                                <div class="vertical_arrow_text_field">
                                                  <p id="output_size_preview_height">50<span> мм</span></p>
                                                </div>
                                                <div class="vertical_arrow_line"></div>
                                                <div class="vertical_arrow_bottom_arrow"></div>
                                          </div>
                                    </div>
                                    <div class="file_preview_cnt_input_wraper_drop_area_design_cut_corner_radius_cnt">
                                          <div class="corner_radius_arrow">
                                                <div class="corner_radius_arrow_row"></div>
                                                <div class="corner_radius_arrow_line"></div>
                                                <div class="corner_radius_arrow_text_field">
                                                    <p id="output_size_preview_corner_radius">5</p>
                                                </div>
                                          </div>
                                    </div>
                            </div>
                        </div> 
                    </div>
                  </div>
                </div>


            </div>
        </div>


${DOMCalculatorResult.part}
`
}

