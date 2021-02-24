# Private Docker Registry ![docker registry]('docker.png')

Before you begin to deploy this deployment you should generate self-signed certificates by OpenSSL.

* Generate a Private Key and a CSR `~$ openssl req -newkey rsa:2048 -nodes -keyout domain.key -out domain.csr` (Note! You should specify hostname which will be used where Private Registry will be deployed.)
* Generate a Self-Signed Certificate from an Existing private key `~$ openssl req -key domain.key -new -x509 -days 365 -out domain.crt` (Specify the same attributes as the first step)
* After generating private key and certificate you should mount the directory which consists those into your deployment with the volume
