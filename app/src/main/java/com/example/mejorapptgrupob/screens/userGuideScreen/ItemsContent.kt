package com.example.mejorapptgrupob.screens.userGuideScreen

sealed class ItemsContent(
    var image: Int?,
    var title: String,
    var details: String
){
    object Item1: ItemsContent(
        null,
        "Info. sobre el test",
        "El test consiste en una serie de 20 preguntas formadas por diferentes profesores del IES Gregorio Prieto que recibieron" +
                " una formación sobre la ansiedad. Esta formación proviene de la clínica Lazos, " +
                "específicamente por Cristina Castillo encargada de transmitir una base sólida, una rápida " +
                "identificación y formas de actuación ante una situación de este tipo.\n" +
                "Este test evaluará 3 factores distintos en base a nuestras respuestas, y cada factor tendrá 3 niveles (bajo, medio y alto)." +
                "\n\n\t- Factor cognitivo: " + "Respuesta cognitiva de ansiedad, indicando los pensamientos con sesgo" +
        " negativo y preocupaciones sobre las situaciones previas, durante y posteriores de" +
                " los exámenes." +
                "\n\n\t- Factor fisiológico: Respuesta fisiológica a la ansiedad" +
                "\n\n\t- Factor de evitación:  Comportamientos de evitación, mostrándose la respuesta motora ansiosa de huida ante las situaciones de los exámenes." +
                "\n\nUna vez finalizado el test se mostrarán diversos consejos dependiendo de los niveles de riesgo en estos factores.\n"+
                "\nEs posible consultar tus resultados previos desde el apartado 'Resultados Anteriores' en el menú de usuario."
    )
    object Item2: ItemsContent(
        null,
        "Info. de interés",
        "En información de interés podrás encontrar diferentes formas de contacto: " +
                " \n\n- El correo electrónico del departamento de orientación del IES Gregorio Prieto, donde podrás hablar con ellos" +
                " y reuniros en persona." +
                "\n\n- Dispondrás del teléfono del menor en el cuál siempre habrá una persona para ayudarte en cualquier momento." +
                "\n\n- Por último dispondrás del teléfono contra el bullying donde te propondrán soluciones" +
                " sobre su situación actual."
    )
    object Item3: ItemsContent(
        null,
        "Sobre nosotros",
        "Somos un grupo de alumnos de 2º CFGS Desarrollo de Aplicaciones Multiplataforma que nos hemos lanzado " +
                "en este gran proyecto con tecnologías totalmente desconocidas. Dicho grupo se compone por:" +
                "\n\n\t - Abdelhaq Boulboul" +
                "\n\t - Adrián Santos-Olmo Díaz" +
                "\n\t - María del Carmen Díaz" +
                "\n\t - Andrés Pisa" +
                "\n\t - Sergio Guijarro" +
                "\n\nPor último, pero no menos importante, queríamos darle un especial agradecimiento " +
                "a nuestros profesores Manuel José Abaldea García y a David Muñoz por monitorizarnos y enseñarnos cómo " +
                "se trabaja en un entorno empresarial, y también agradecer a aquellos docentes y alumn@s" +
                "que han aportado su granito de arena para que esta aplicación pudiera salir hacia delante."
    )
}
