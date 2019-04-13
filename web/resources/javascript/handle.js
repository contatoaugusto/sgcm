/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function atribuiPacienteTitleAgenda (){
    
     var paciente = document.getElementById("form_cadastro:paciente_input");
     var titleAgendaConsulta = document.getElementById("form_dialogEvent:titleAgendaConsulta");
     titleAgendaConsulta.value = paciente.value;

}

