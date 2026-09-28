package kernelmjr.manual;

/**
 * Tabela Hash manual (Mapa de Chave-Valor) usando Encadeamento Separado.
 *
 * 🗂️ ANALOGIA NO MUNDO REAL E EVOLUÇÃO (Sprint 06):
 * 
 * 1. O que é?
 *    Imagine que você trabalha num arquivo médico com milhares de pastas. Em vez de olhar pasta por pasta, 
 *    você olha o nome "João", aplica uma fórmula na cabeça que te diz: "Gaveta 42, pasta 3!".
 * 
 * 2. A Evolução Mágica da Busca O(n) para O(1):
 *    - Num Array comum ou LinkedList, para achar um elemento pelo nome, você precisa 
 *      perguntar de um por um: "Você é o João?" (Custo O(n)).
 *    - A Hash Table pega o nome (Chave), joga num triturador matemático (Hash Function), 
 *      e tira um número mágico (Índice). Com esse índice, ela vai *direto* na gaveta certa no Array (O(1)).
 *    - E se duas chaves caírem na mesma gaveta? (Colisão). Resolvemos colocando uma LinkedList lá dentro!
 *
 * Motivo didatico: Ensinar como Hash Tables quebram a barreira do O(n) na busca.
 * Demonstra a funcao de espalhamento (Hash) e como tratar Colisoes (quando duas 
 * chaves diferentes caem no mesmo indice) sem usar o java.util.HashMap.
 */
public final class Sprint06ManualHashTable<K, V> {
    
    /**
     * Objeto simples (Tupla) que armazena a relacao Chave -> Valor.
     */
    public static final class Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    // O "vetor principal" da tabela. Em vez de armazenar o dado direto, 
    // cada indice armazena uma Lista Encadeada (Sprint02ManualLinkedList) chamada de "Bucket" (Balde).
    // Isso resolve colisoes: se dois dados cairem na mesma gaveta, ambos sao enfileirados na lista.
    private final Sprint02ManualLinkedList<Entry<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public Sprint06ManualHashTable() {
        // Inicializa o array de gavetas (buckets). O tamanho deve preferencialmente ser 
        // potencia de 2 (aqui 32) para otimizar operacoes binarias de modulo.
        buckets = (Sprint02ManualLinkedList<Entry<K, V>>[]) new Sprint02ManualLinkedList[32];
    }

    public int size() {
        return size;
    }

    /**
     * Insere ou atualiza um par Chave-Valor.
     * Custo Amortizado: O(1).
     * 
     * @param key Chave unica de identificacao.
     * @param value O valor a ser atrelado.
     */
    public void put(K key, V value) {
        int index = bucketIndex(key);
        Sprint02ManualLinkedList<Entry<K, V>> bucket = ensureBucket(index);
        
        // Verifica se a chave ja existe na gaveta para apenas atualizar (Update) o valor.
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (sameKey(entry.key, key)) {
                entry.value = value;
                return;
            }
        }
        
        // Caso nao exista, insere um novo Entry no final da lista ligada.
        bucket.addLast(new Entry<>(key, value));
        size++;
    }

    /**
     * Busca um valor pela Chave.
     * Custo Amortizado: O(1). Acesso matematico direto ao bucket.
     * Se a funcao de hash for ruim e tudo cair na mesma gaveta, vira uma busca linear O(n).
     * 
     * @param key A chave a ser procurada.
     */
    public V get(K key) {
        int index = bucketIndex(key);
        Sprint02ManualLinkedList<Entry<K, V>> bucket = buckets[index];
        
        if (bucket == null) {
            return null; // Gaveta completamente vazia
        }
        
        // Busca linear O(k) somente dentro da gaveta especifica (k = num. colisoes no bucket).
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (sameKey(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Remove o par Chave-Valor da tabela.
     * Custo Amortizado: O(1).
     */
    public V remove(K key) {
        int index = bucketIndex(key);
        Sprint02ManualLinkedList<Entry<K, V>> bucket = buckets[index];
        
        if (bucket == null) {
            return null;
        }
        
        // Em vez de usar remocao complexa na lista, criamos uma nova lista 
        // apenas com os dados que SOBRARAM, ignorando o que o usuario pediu pra remover.
        Sprint02ManualLinkedList<Entry<K, V>> rebuilt = new Sprint02ManualLinkedList<>();
        V removed = null;
        
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (sameKey(entry.key, key)) {
                removed = entry.value; // Encontrou! Salva o valor, mas nao adiciona no rebuild.
            } else {
                rebuilt.addLast(entry);
            }
        }
        
        // Se a gaveta ficar vazia, anula ela para economizar memoria (Garbage Collector).
        buckets[index] = rebuilt.isEmpty() ? null : rebuilt;
        
        if (removed != null) {
            size--;
        }
        return removed;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    /**
     * Visita todas as entradas da tabela usando o padrao Visitor.
     * Custo: O(n), afinal iterar por TUDO exige passar por N elementos.
     */
    public void forEach(Sprint10EntryVisitor<K, V> visitor) {
        for (int i = 0; i < buckets.length; i++) {
            Sprint02ManualLinkedList<Entry<K, V>> bucket = buckets[i];
            if (bucket == null) {
                continue; // Gaveta vazia, pula.
            }
            
            // Varre a gaveta e envia pro visitante.
            for (int j = 0; j < bucket.size(); j++) {
                Entry<K, V> entry = bucket.get(j);
                visitor.visit(entry.key, entry.value);
            }
        }
    }

    /**
     * Garante que a gaveta do array ja contem uma LinkedList instanciada.
     */
    private Sprint02ManualLinkedList<Entry<K, V>> ensureBucket(int index) {
        Sprint02ManualLinkedList<Entry<K, V>> bucket = buckets[index];
        if (bucket == null) {
            bucket = new Sprint02ManualLinkedList<>();
            buckets[index] = bucket;
        }
        return bucket;
    }

    /**
     * FUNCAO DE ESPALHAMENTO (HASHING).
     * Transforma qualquer dado arbitrario (String, Objeto) em um Numero Inteiro, 
     * e forca que esse numero caiba dentro dos limites do nosso array (Ex: 0 a 31).
     */
    private int bucketIndex(Object key) {
        int hash = key == null ? 0 : key.hashCode();
        
        // Mascara binaria 0x7fffffff corta o sinal negativo para evitar um IndexOutOfBounds.
        // O operador modulo (%) restringe o numero ao tamanho do array (buckets.length).
        return (hash & 0x7fffffff) % buckets.length;
    }

    private boolean sameKey(Object first, Object second) {
        return first == second || (first != null && first.equals(second));
    }
}
