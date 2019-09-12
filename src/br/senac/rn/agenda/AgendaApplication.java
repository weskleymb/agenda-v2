package br.senac.rn.agenda;

import br.senac.rn.agenda.model.Contato;
import br.senac.rn.agenda.repository.ContatoRepository;

import java.util.Optional;

public class AgendaApplication {

    public static void main(String[] args) {
        ContatoRepository repository = new ContatoRepository();
//        for (Contato contato : repository.findAll()) {
//            String id = contato.getId().toString();
//            String nome = contato.getNome();
//            String fone = contato.getFone();
//            String resultado = id + " / " + nome + " / " + fone;
//            System.out.println(resultado);
//        }
        Optional<Contato> contato = repository.findById(5L);
        if (contato.isPresent()) {
            System.out.println(contato.get().getNome());
        } else {
            System.out.println("Não tem");
        }
    }

}
