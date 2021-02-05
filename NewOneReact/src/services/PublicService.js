import {Network} from "@share/network";
import {getSex, getZxbs} from "@/pages/administrate/routes/UpdateUser/hooks";

/**
**/
class PublicService {
    network;

    constructor(network) {
        this.network = network;
    }


    /**
* @typedef {object} GetUserRequest
* @property {string} id 用户信息表id
*/
    /**
     * @typedef {object} GetUserResponse
     * @property {string} [uuid] 用户UUID
     * @property {string} [account] 用户账号
     * @property {string} [password] 用户密码
     * @property {string} [phone] 用户手机号
     * @property {string} [email] 用户邮箱
     * @property {string} [createUserId] 创建人员
     * @property {string} [createTime] 创建时间，时间格式yyyy-MM-dd HH:mm:ss
     * @property {string} [updateUserId] 更新人员
     * @property {string} [updateTime] 更新时间，时间格式yyyy-MM-dd HH:mm:ss
     * @property {string} [sex] 用户性别（引用表码）
对应表码名EN_SEX
     * @property {string} [_cn_sex] 用户性别（引用表码） 表码翻译
     * @property {string} [district] 用户所属区（引用区表ID）
对应表码名EN_DISTRICT
     * @property {string} [_cn_district] 用户所属区（引用区表ID） 表码翻译
     * @property {string} [loginTime] 用户最后登录时间，时间格式yyyy-MM-dd HH:mm:ss
     */

    /**
     * 用户信息表-详情获取
     * @async
     * @param {GetUserRequest} getUserRequest
     * @return {Promise<GetUserResponse>} 返回值
     */
    getUser = async getUserRequest => {
        return await this.network.formGet('/newOne/user/getUser.do', getUserRequest);
    }

    /**
     * @typedef {object} UpdateUserRequest
     * @property {string} uuid 用户UUID
     * @property {string} [account] 用户账号
     * @property {string} [password] 用户密码
     * @property {string} [phone] 用户手机号
     * @property {string} [email] 用户邮箱
     * @property {string} [sex] 用户性别（引用表码）
对应表码名EN_SEX
     * @property {string} [district] 用户所属区（引用区表ID）
对应表码名EN_DISTRICT
     * @property {string} [zxbs] 注销标识，0：未注销、1：注销
对应表码名EN_ZXBS
     */

    /**
     * @typedef {object} UpdateUserResponse
     * @property {string} id 主表主键
     */

    /**
     * 用户信息表-修改
     * @async
     * @param {UpdateUserRequest} updateUserRequest
     * @return {Promise<UpdateUserResponse>} 返回值
     */
    updateUser = async updateUserRequest => {
        return await this.network.json('/newOne/user/updateUser.do', updateUserRequest);
    }

    /**
     * 列表查询请求对象
     * @typedef {object} ListRequest
     * @property {object} [page] 分页条件
     * @property {integer} [page.currentPage=&#34;1&#34;] 查询页码
     * @property {integer} [page.linesPerPage=&#34;10&#34;] 每页条数
     * @property {array} [page.orderBys]
     * @property {object} [data]
     * @property {string} [data.account] 用户账号
     * @property {string} [data.password] 用户密码
     * @property {string} [data.phone] 用户手机号
     * @property {string} [data.email] 用户邮箱
     * @property {string} [data.createUserId] 创建人员
     * @property {string} [data.createTime] 创建时间，时间格式yyyy-MM-dd HH:mm:ss
     * @property {string} [data.updateUserId] 更新人员
     * @property {string} [data.updateTime] 更新时间，时间格式yyyy-MM-dd HH:mm:ss
     * @property {string} [data.sex] 用户性别（引用表码）
对应表码名EN_SEX
     * @property {string} [data.district] 用户所属区（引用区表ID）
对应表码名EN_DISTRICT
     * @property {string} [data.loginTime] 用户最后登录时间，时间格式yyyy-MM-dd HH:mm:ss
     * @property {string} [data.zxbs] 注销标识，0：未注销、1：注销
对应表码名EN_ZXBS
     * @property {boolean} [ignoreCache=false] 是否忽略缓存
     */

    /**
     * ShareResponseProp
     * @typedef {object} ListResponse
     * @property {string} [schemeId] 方案ID
     * @property {object} [page] PageOut
     * @property {integer} [page.currentPage] 查询页码
     * @property {integer} [page.linesPerPage] 每页条数
     * @property {integer} [page.totalNum] 总条数
     * @property {integer} [page.totalPage] 总页码
     * @property {array} [list] 查询返回列表数据
     * @property {boolean} [fromCache=false] 是否来自缓存
     * @property {string} [cacheTime] 缓存时间，时间格式yyyy-MM-dd hh:mm:ss
     * @property {object} [request] 列表查询请求对象
     * @property {object} [request.page] 分页条件
     * @property {integer} [request.page.currentPage=&#34;1&#34;] 查询页码
     * @property {integer} [request.page.linesPerPage=&#34;10&#34;] 每页条数
     * @property {array} [request.page.orderBys]
     * @property {object} [request.data]
     * @property {boolean} [request.ignoreCache=false] 是否忽略缓存
     */

    /**
     * 用户信息表-列表查询
     * @async
     * @param {ListRequest} listRequest
     * @return {Promise<ListResponse>} 返回值
     */
    list = async listRequest => {
        return await this.network.json('/newOne/user/list.do', listRequest);
    }

    /**
     * 对象ID集合
     * @typedef {array} DeleteUserRequest
     */

    /**
     * @typedef {object} DeleteUserResponse
     * @property {boolean} [result]
     */

    /**
     * 用户信息表-删除
     * @async
     * @param {DeleteUserRequest} deleteUserRequest
     * @return {Promise<DeleteUserResponse>} 返回值
     */
    deleteUser = async deleteUserRequest => {
        return await this.network.json('/newOne/user/deleteUser.do', deleteUserRequest);
    }

    /**
     * @typedef {object} AddUserRequest
     * @property {string} [account] 用户账号
     * @property {string} [password] 用户密码
     * @property {string} [phone] 用户手机号
     * @property {string} [email] 用户邮箱
     * @property {string} [sex] 用户性别（引用表码）
对应表码名EN_SEX
     * @property {string} [district] 用户所属区（引用区表ID）
对应表码名EN_DISTRICT
     * @property {string} [zxbs] 注销标识，0：未注销、1：注销
对应表码名EN_ZXBS
     */

    /**
     * @typedef {object} AddUserResponse
     * @property {string} id 主表主键
     */

    /**
     * 用户信息表-新增
     * @async
     * @param {AddUserRequest} addUserRequest
     * @return {Promise<AddUserResponse>} 返回值
     */
    addUser = async addUserRequest => {
        return await this.network.json('/newOne/user/addUser.do', addUserRequest);
    }

    getData = async getDataRequest => {
        const sexValue = await this.network.formGet('/newOne/data/getSex.do', getDataRequest);
        const zxbsValue = await this.network.formGet('/newOne/data/getZxbs.do', getDataRequest);
        return  await this.network.formGet('/newOne/data/getSex.do', getDataRequest);
        // console.log(sexValue)
        // console.log(zxbsValue)
    };
}
export default PublicService;
