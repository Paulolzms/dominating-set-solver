# Paulo Luiz Mendes Souza | 21.1.8011

# Variáveis
JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin
MAIN_CLASS = Main

# Detecta se está no Windows ou Linux
ifeq ($(OS),Windows_NT)
    MKDIR = if not exist $(BIN_DIR) mkdir $(BIN_DIR)
    RMDIR = rmdir /S /Q $(BIN_DIR)
else
    MKDIR = mkdir -p $(BIN_DIR)
    RMDIR = rm -rf $(BIN_DIR)
endif

# Alvo padrão: compila e executa o programa
all: build run

# Compila os arquivos .java do diretório de origem para o diretório bin
build:
	@echo "Compilando os arquivos Java..."
	$(MKDIR)
	$(JAVAC) -d $(BIN_DIR) $(SRC_DIR)/*.java

# Executa o programa
run:
	@echo "Executando o programa..."
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

# Remove os arquivos compilados
clean:
	@echo "Limpando arquivos compilados..."
	$(RMDIR)

# Ajuda
help:
	@echo "Comandos disponíveis:"
	@echo "  make          - Compila e executa o programa"
	@echo "  make build    - Apenas compila os arquivos Java"
	@echo "  make run      - Executa o programa compilado"
	@echo "  make clean    - Remove os arquivos compilados"
	@echo "  make help     - Exibe os comandos disponíveis"
