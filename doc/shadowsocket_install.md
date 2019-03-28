- yum groupinstall "Development Tools" 
- yum install m2crypto python-setuptools  
- easy_install pip 
- pip install shadowsocks  
- pip install --upgrade pip
- vim /etc/shadowsocks.json
``````aidl
{
    "server":"0.0.0.0",
    "server_port":1088,
    "local_address":"127.0.0.1",
    "local_port":1080,
    "password":"yan432464",
    "timeout":300,
    "method":"aes-256-cfb",
    "fast_open":false
}

``````
vim start_shadowsocket_server.sh
``````aidl
    #!/bin/bash
    # 开启vpn

    ssserver -c /etc/shadowsocks.json -d start
``````
sh start_shadowsocket_server.sh