package kernelmjr.demo;

import kernelmjr.manual.Sprint10EntryVisitor;
import kernelmjr.manual.Sprint06ManualHashTable;

/**
 * Demo generica para a Sprint 06 (Sprint06ManualHashTable).
 * Ensinando Dicionarios Chave-Valor usando um banco de dados de 'Produtos'.
 */
public final class Sprint06ManualHashTableDemo {
    
    private Sprint06ManualHashTableDemo() {
    }

    public static void main(String[] args) {
        
        // Mapeamento: Chave (ID/String) -> Valor (Nome do Produto/String)
        Sprint06ManualHashTable<String, String> estoque = new Sprint06ManualHashTable<>();

        System.out.println("=== EXEMPLO GENERICO - HASH TABLE ===");
        
        // 1. Insercao via Espalhamento (O(1) amortizado)
        estoque.put("SKU-100", "Notebook 16GB");
        estoque.put("SKU-200", "Mouse Sem Fio");
        estoque.put("SKU-300", "Teclado Mecanico");
        
        System.out.println("Itens mapeados: " + estoque.size());
        
        // 2. Busca ultra-rapida via Hash (O(1))
        System.out.println("Buscando por SKU-200: " + estoque.get("SKU-200"));
        System.out.println("Buscando por SKU-999: " + estoque.get("SKU-999")); // Nao existe
        
        System.out.println("--------------------------------");
        
        // 3. Atualizacao (Sobrescrita do mesmo Hash)
        estoque.put("SKU-200", "Mouse Sem Fio (Bluetooth V5)");
        System.out.println("SKU-200 Atualizado: " + estoque.get("SKU-200"));
        
        // 4. Remocao e Iteracao (Visitor)
        estoque.remove("SKU-300");
        System.out.println("SKU-300 removido.");
        System.out.println("Estoque atual:");
        
        estoque.forEach(new Sprint10EntryVisitor<String, String>() {
            @Override
            public void visit(String key, String value) {
                System.out.printf("  -> [%s]: %s%n", key, value);
            }
        });

        System.out.println("--------------------------------");
        System.out.println("DICA DA SPRINT: Sistemas Operacionais usam Hash Tables para resolver Caminhos de Arquivos (Path -> File Object) e Cache de Memoria (Pagina -> Endereco Fisico). Onde voce conectaria isso no seu Kernel?");
    }
}
