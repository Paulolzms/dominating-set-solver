
# 📘 Problema do Conjunto Dominante em Grafos

Este projeto tem como objetivo resolver o **Problema do Conjunto Dominante** utilizando duas abordagens:

- Um **algoritmo guloso**, que oferece uma solução aproximada de forma eficiente;
- Uma **meta-heurística** (Simulated Annealing), usada para comparação de desempenho e qualidade da solução.

O projeto também inclui geração automática de instâncias de grafos aleatórios e análise empírica de desempenho com validação da complexidade.

---

## 🔎 O Problema

Dado um grafo não direcionado \( G = (V, E) \), o problema consiste em encontrar um **subconjunto de vértices \( D \subseteq V \)** tal que:

- Todo vértice em \( V \) está em \( D \) ou é vizinho de algum vértice em \( D \);
- O conjunto \( D \) deve ter **tamanho mínimo**.

Este problema é **NP-completo** e tem diversas aplicações em redes, cobertura, alocação de recursos e mais.

---

## 🧠 Algoritmos Implementados

### ✔️ Algoritmo Guloso
- Seleciona iterativamente o vértice com o maior número de vizinhos não dominados.
- Simples, rápido e eficiente em grafos esparsos.
- Complexidade:  
  - \( O(V^2) \) para grafos esparsos  
  - Até \( O(V^3) \) para grafos densos

### 🔥 Meta-heurística (Simulated Annealing)
- Estratégia inspirada no processo de resfriamento de metais.
- Explora soluções vizinhas com aceitação probabilística.
- Mais eficiente em encontrar soluções menores para instâncias densas.

---

## 🧪 Geração de Instâncias

As instâncias de grafo são geradas aleatoriamente com os seguintes parâmetros:

- Número de vértices \( V \)
- Probabilidade de aresta \( p \in [0,1] \)
- Representação: **lista de adjacência**

---

## 📈 Análise Experimental

- Foram coletados tempos de execução para grafos com diferentes tamanhos e densidades.
- Três abordagens foram usadas para validar empiricamente a complexidade:
  1. Regressão polinomial
  2. Normalização por complexidade esperada
  3. Comparação com curvas teóricas

Resultados confirmaram:
- **Crescimento quadrático** para grafos esparsos
- **Crescimento cúbico** para grafos densos

---

## ▶️ Como Executar

### Pré-requisitos:
- Java (JDK 8+)
- Make (Linux ou MinGW/WSL no Windows)

### Compilar e Executar:
```bash
make          # Compila e executa
make build    # Apenas compila
make run      # Apenas executa
make clean    # Remove arquivos .class
```

---

## 📂 Estrutura do Projeto

```
.
├── src/
│   ├── ConjuntoDominanteGuloso.java
│   ├── SimulatedAnnealing.java
│   ├── GeradorDeInstancias.java
│   └── ...
├── bin/              # Saída da compilação
├── dados/            # Arquivos de entrada/saída
├── resultados/       # Logs de execução
├── README.md
└── Makefile
```

---

## 👨‍💻 Autor

**Paulo Luiz M. Souza**  
Universidade Federal de Ouro Preto  
João Monlevade - MG - Brasil

---

## 📃 Licença

Este projeto é acadêmico e não possui uma licença específica. Sinta-se livre para estudar, modificar e compartilhar para fins educacionais.
