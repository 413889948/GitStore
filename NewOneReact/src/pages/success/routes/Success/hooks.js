import Network from "@share/network";
import Dialog from "@/components/Dialog/Dialog";

export const transition = async () => {
    const value = await Network.formGet('/newOne/transition.do');
    if(value.flag){
        switch (value.role) {
            case "1":
                window.location.replace("/administrate")
            case "2":
                await Dialog.alert('欢迎登录新人考核项目');
                window.location.href="/newOne/index.html"
                return ;
            default:
                window.location.replace("/")
        }
    }else {
        window.location.replace("/")
    }

};

