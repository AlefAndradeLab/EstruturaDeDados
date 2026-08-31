package kernel.demo;

import kernel.sprint.sprint01.ManualArray;

public final class Sprint01ManualArrayDemo {
    private Sprint01ManualArrayDemo() {

    }

    public static void main(String[] args) {
        ManualArray<String> processo = new ManualArray<>(5);

        processo.add("001");
        processo.add("002");
        processo.add("003");

        processo.insertAt(1, "004");

        processo.add("005");

        System.out.println ("========= MANUAL ARRAY =========");
        System.out.println ("Tamanho atual: " + processo.size());
        System.out.println ("Gerenciador de processos (indice -> nome)");
        System.out.println ("--------------------------------");

        for (int i = 0; i < processo.size(); i++) {
            System.out.printf ("[%d] -> %s%n", i, processo.get(i));
        }

        
        System.out.println ("--------------------------------");
        System.out.println ("indice 0: " + processo.get(0));
        System.out.println ("indice 2: " + processo.get(2));
        System.out.println ("indice 4: " + processo.get(4));
        System.out.println ("--------------------------------");

        String removido = processo.removeAt(3);
        System.out.println ("Removido em indice 3: " + removido);
        System.out.println ("Tamanho apos remocao: " + processo.size());
        
        System.out.println ("Tabela apos compactacao: ");

        for (int i = 0; i < processo.size(); i++) {
            System.out.printf ("[%d] -> %s%n", i, processo.get(i));
        }

        processo.add("006");
        processo.add("007");
        processo.add("008");

        System.out.println ("--------------------------------");
        System.out.println("Tamanho após redimensionamento: " + processo.size());
        System.out.println("Tabela após redimensionamento: ");

        for (int i = 0; i < processo.size(); i++) {
            System.out.printf ("[%d] -> %s%n", i, processo.get(i));
        }

        System.out.println ("--------------------------------");
    }
}