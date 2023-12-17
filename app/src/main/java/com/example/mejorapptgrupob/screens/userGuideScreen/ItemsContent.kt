package com.example.mejorapptgrupob.screens.userGuideScreen

sealed class ItemsContent(
    var image: Int?,
    var title: String,
    var details: String
){
    object Item1: ItemsContent(
        null,
        "Info. sobre el test",
        "Pendiente para la mención de créditos sobre los creadores de las preguntas"
    )
    object Item2: ItemsContent(
        null,
        "Info. de interés",
        "En información de interés podrás encontrar estas formas de contacto: " +
                " \n\n- El departamento de orientación del IES Gregorio Prieto donde podrás hablar con ellos" +
                " y reuniros en persona." +
                "\n\n- Dispondrás del teléfono del menor en el cuál siempre habrá una persona para ayudarte en cualquier momento." +
                "\n\n- Por último dispondrás del teléfono contra el bullying donde te propondrán soluciones" +
                " sobre tu situación actual, no tienes porque seguir sufriendo, no es la vida que te mereces"
    )
    object Item3: ItemsContent(
        null,
        "Sobre nosotros",
        "Somos un grupo de alumnos de 2º Desarrollo de Aplicaciones Multiplataforma que nos hemos lanzado " +
                "en este gran proyecto con tecnologías totalmente desconocidas." +
                "\n\n\t - Abdelhaq Boulboul" +
                "\n\t - Adrían Santos-Olmo Díaz" +
                "\n\t - María del Carmen Díaz" +
                "\n\t - Andrés Pisa" +
                "\n\t - Sergio Guijarro" +
                "\n\nPor último, pero no menos importante, queríamos darle un especial agradecimiento " +
                "a nuestros profesores José Abaldea García y a David Muñoz por monitorizarnos y enseñarnos cómo " +
                "se trabaja en un entorno empresarial. También, agradecemos a aquellos profesores del centro " +
                "que han aportado su granito de arena para que esta aplicación pudiera salir hacia delante."
    )
}
