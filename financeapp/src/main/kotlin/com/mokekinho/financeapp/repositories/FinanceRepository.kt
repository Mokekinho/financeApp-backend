package com.mokekinho.financeapp.repositories


import com.mokekinho.financeapp.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository


interface FinanceRepository: JpaRepository<UserEntity, Long>{
    fun findByName(name: String): UserEntity?
}
/*
* Essa é uma das funcionalidades mais poderosas do Spring Data JPA, chamada **Query Methods**. O Spring "lê" o nome do método e traduz para SQL automaticamente.

Para isso funcionar, o nome do método deve seguir o padrão: **[Ação] + [Campo] + [Condição]**.

Aqui estão os exemplos mais comuns divididos por categoria:

### 1. Buscas Simples (Igualdade)

Sempre começam com `find`, `read`, `get` ou `query`.

* **`findByName(name: String)`**: Busca exata. `WHERE name = ?`
* **`findByEmailAndStatus(email: String, status: String)`**: Usa o operador `AND`.
* **`findByAgeOrActive(age: Int, active: Boolean)`**: Usa o operador `OR`.

### 2. Comparação de Strings (Buscas Parciais)

Muito útil para campos de busca (search bars).

* **`findByNameContaining(part: String)`**: O famoso `LIKE %part%`.
* **`findByNameStartingWith(prefix: String)`**: Começa com. `LIKE prefix%`.
* **`findByNameIgnoreCase(name: String)`**: Ignora se é maiúsculo ou minúsculo.

### 3. Comparação Numérica e Datas

* **`findByPriceLessThan(value: Double)`**: Menor que. `<`.
* **`findByAgeBetween(start: Int, end: Int)`**: Entre dois valores. `BETWEEN ? AND ?`.
* **`findByCreatedAtAfter(date: Instant)`**: Datas posteriores a uma referência. `>`.

### 4. Ordenação e Limitação

* **`findFirstByOrderByCreatedAtDesc()`**: Pega o registro mais recente (o primeiro da lista decrescente).
* **`findTop3ByActiveTrue()`**: Pega apenas os 3 primeiros que estão ativos.
* **`findByNameOrderByNameAsc(name: String)`**: Busca e já traz em ordem alfabética.

---

### Resumo de Palavras-Chave (Tabela de Tradução)

| Palavra no Método | Tradução SQL |
| --- | --- |
| `Containing` / `Like` | `LIKE %?%` |
| `GreaterThan` | `> ?` |
| `IsNotNull` | `IS NOT NULL` |
| `True` / `False` | `IS TRUE` / `IS FALSE` |
| `In(list)` | `IN (?, ?, ?)` |

---

### Exemplo Prático no seu `FinanceRepository`:

```kotlin
interface FinanceRepository : JpaRepository<UserEntity, Long> {

    // 1. Buscar usuário pelo nome exato
    fun findByName(name: String): UserEntity?

    // 2. Verificar se um e-mail já existe (retorna boolean)
    fun existsByEmail(email: String): Boolean

    // 3. Buscar todos os administradores ativos
    fun findByRoleAndActiveTrue(role: Role): List<UserEntity>

    // 4. Buscar usuários criados após uma data, ordenados por nome
    fun findByCreatedAtAfterOrderByNameAsc(date: Instant): List<UserEntity>
}

```

### ⚠️ Regras de Ouro:

1. **CamelCase é obrigatório:** Se o seu campo na classe é `birthDate`, o método precisa ser `findByBirthDate`.
2. **Cuidado com campos inexistentes:** Se você escrever `findByNome` (com "o"), mas na classe o campo for `name`, o projeto nem vai compilar. O Spring valida isso ao subir a aplicação.

Qual dessas buscas você precisa implementar agora para o seu sistema de finanças? Posso te ajudar a montar a lógica do Service para usá-la.
* */
