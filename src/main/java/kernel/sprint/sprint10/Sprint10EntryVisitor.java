package kernelmjr.manual;

/**
 * Interface Callback simples para percorrer (visitar) entradas de um mapa/hash.
 *
 * Motivo didatico: Padrao de projeto Visitor (GoF). 
 * Evita expor a estrutura interna (como a lista ligada ou arrays) para quem 
 * esta chamando, garantindo o encapsulamento. Quem chama so se preocupa
 * com a Chave e o Valor encontrados.
 */
public interface Sprint10EntryVisitor<K, V> {
    
    /**
     * Metodo executado automaticamente para cada entrada chave/valor encontrada.
     * 
     * @param key A chave da entrada.
     * @param value O valor da entrada.
     */
    void visit(K key, V value);
}
