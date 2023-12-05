package com.example.mejorapptgrupob.screens.userGuideScreen

sealed class ItemsContent(
    var image: Int?,
    var title: String,
    var details: String
){
    object Item1: ItemsContent(
        null,
        "Info. sobre el test",
        "Información sobre el test. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam condimentum consequat felis. Nulla sit amet nisl et leo tristique volutpat. Praesent at lorem elit. Ut urna risus, sagittis sit amet velit consectetur, iaculis congue ante. Pellentesque interdum, nibh sit amet blandit luctus, est lacus convallis lorem, non sollicitudin ex nisi tincidunt arcu. Donec ultrices elit eget turpis mollis luctus sit amet sit amet metus. Vivamus fringilla quam et venenatis semper. Aenean eu eleifend erat, ut elementum metus. "
    )
    object Item2: ItemsContent(
        null,
        "Info. de interés",
        "Información de interés. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam condimentum consequat felis. Nulla sit amet nisl et leo tristique volutpat. Praesent at lorem elit. Ut urna risus, sagittis sit amet velit consectetur, iaculis congue ante. Pellentesque interdum, nibh sit amet blandit luctus, est lacus convallis lorem, non sollicitudin ex nisi tincidunt arcu. Donec ultrices elit eget turpis mollis luctus sit amet sit amet metus. Vivamus fringilla quam et venenatis semper. Aenean eu eleifend erat, ut elementum metus. "
    )
    object Item3: ItemsContent(
        null,
        "Sobre nosotros",
        "Información sobre los desarrolladores. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam condimentum consequat felis. Nulla sit amet nisl et leo tristique volutpat. Praesent at lorem elit. Ut urna risus, sagittis sit amet velit consectetur, iaculis congue ante. Pellentesque interdum, nibh sit amet blandit luctus, est lacus convallis lorem, non sollicitudin ex nisi tincidunt arcu. Donec ultrices elit eget turpis mollis luctus sit amet sit amet metus. Vivamus fringilla quam et venenatis semper. Aenean eu eleifend erat, ut elementum metus. "
    )
}
