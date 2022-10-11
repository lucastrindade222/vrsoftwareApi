
echo "Inicializando Build Java"
./mvnw clean package -DskipTests
echo "Fim Build Java"

echo "Inicializando Build Image Docker."

sudo docker build . -t vr

echo "Lista image Docker."

sudo docker images 

echo "Fim Build Image Docker."

