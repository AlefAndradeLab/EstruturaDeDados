package kernel.sprint.sprint01;

public class ArmazenaProcesso {

    public static void main(String[] args) {
        ManualArray<String> processo = new ManualArray<>(5);

        processo.add("110");
        processo.add("120");
        processo.add("130");

        processo.insertAt(2, "150");

        System.out.println ("== EXEMPLO GENERICO - MANUAL ARRAY ===");
        System.out.println ("Tamanho atual: " + processo.size());
        System.out.println ("Tabela de processos (indice -> nome)");
        System.out.println ("--------------------------------");

        for (int i = 0; i < processo.size(); i++) {
            System.out.printf ("[%d] -> %s%n", i, processo.get(i));
        }

        

    }   
}
