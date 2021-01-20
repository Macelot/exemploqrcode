# exemploqrcode
Coloque suas credenciais na classe Auth (client_id e client_secret)

Na classe Auth coloque seu certificado na pasta do projeto ou configure um local de sua preferência

Ajuste o payload da Classe Cob conforme suas necessidades

O projeto realiza o seguinte fluxo:

1)Vamos autenticar [POST - https://api-pix.gerencianet.com.br/oauth/token]

2)Criar uma cobrança [PUT - https://api-pix.gerencianet.com.br/v2/cob/{txid}]

3)Emissão do QRCode de um location [GET - https://api-pix.gerencianet.com.br/v2/loc/{id}/qrcode]

4)Salvar e exibir a imagem do QRCode
