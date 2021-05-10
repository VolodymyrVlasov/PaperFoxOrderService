#Stucture calculator

Divide  calculator for forms, forms for components, components for subcomponents.
Calculator structure must have 3 independently components
####In first row 2 independently form contain:
- Calculator input form -> flex 2
- Preview and upload zone form -> flex 1.3

####In second row:
- Calculator result form with 'Add to cart button' -> width 100%

##Form structure
Each form must be responsive and flexible.
It must be independently with universal component id's

##Form component
Divide form for components, like 'NumberedInput' or 'RangeInput'.
Each component must have one of flexible and other fixed sides.
Component children structure must have one SCSS file for component

##Style structure
Use Sass preprocessor and compile result to path './dist/css'
```
.component{
    &_child {
        &_sub_child{
        }
    }
}
```
##Id
id structure is:
```
<component name>_<component_value>_<component sub value>
```
example:
```
id='calculator_size_width'
id='calculator_size_height'
id='calculator_size_corner_radius'
```
- use underscore between words
- each word in lowercase
- do not use word abbreviations