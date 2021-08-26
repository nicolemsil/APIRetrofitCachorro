    var express = require('express');
    var app = express();
    var cors = require('cors');
    var bodyParser = require('body-parser');


    app.use(bodyParser.json());
    app.use(bodyParser.urlencoded({ extended: true }));
    app.use(cors());

    app.get('/api/monitor/get', function (req, res) {
        setTimeout(function(){
        res.header('Access-Control-Allow-Origin', '*')
        .send(200,                    
	[  
	{  
		"nome":"Gabriel Alves",        		
        "image":"https://image.freepik.com/vetores-gratis/vector-avatar-sorrindo-homem-expressao-facial_102172-203.jpg",
		"horario":"Seg - 7h30 às 8h20"
   	},
   	{  
        "nome":"Nicolas Duarte",
      	"image":"https://image.freepik.com/vetores-gratis/homem-jovem-avatar-personagem-vector-ilustracao-design_24877-18550.jpg",
      	"horario":"Ter - 13h às 13h50"
   	},
   	{  
        "nome":"Nouani",
	"image":"https://thumbs.dreamstime.com/b/o-homem-feliz-farpado-mostra-polegar-acima-gesto-fresco-124097791.jpg",
	"horario":"Qua - 18h15 às 19h00"
   	},
   	{  
        "nome":"Rafael Belinelli",
	"image":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkFE0vM7yFk918psCnju8_g7wjLJShngwb1zfpwOOQ1wAgWalATUydjVYeSWIGvs6euuY&usqp=CAU",
  	"horario":"Qui - 17h25 às 18h15"
	},
	{  
        "nome":"Ricardo",
	"image":"https://thumbs.dreamstime.com/b/homem-de-cabelos-longos-avatar-rosto-humano-belo-desenho-personalizado-animado-masculino-cabe%C3%A7a-personagem-182214072.jpg",
  	"horario":"Sex - 20h20 às 21h10"
	},   	
	]
    )
    }, 3000);
    });
  

    app.listen(3000);
    console.log('A API está no ar');