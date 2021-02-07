/**
**/
class TEcNewoneDistrictService {
    network;

    constructor(network) {
        this.network = network;
    }


    /**
     * 对象ID集合
     * @typedef {array} DeleteTEcNewoneDistrictRequest
     */

    /**
     * @typedef {object} DeleteTEcNewoneDistrictResponse
     * @property {boolean} [result]
     */

    /**
     * 区表-删除
     * @async
     * @param {DeleteTEcNewoneDistrictRequest} deleteTEcNewoneDistrictRequest
     * @return {Promise<DeleteTEcNewoneDistrictResponse>} 返回值
     */
    deleteTEcNewoneDistrict = async deleteTEcNewoneDistrictRequest => {
        return await this.network.json('/tEcNewoneDistrict/deleteTEcNewoneDistrict.do', deleteTEcNewoneDistrictRequest);
    }

    /**
     * @typedef {object} UpdateTEcNewoneDistrictRequest
     * @property {integer} id 区表ID
     * @property {string} [definition] 区名
     * @property {string} [value] 区序号
     */

    /**
     * @typedef {object} UpdateTEcNewoneDistrictResponse
     * @property {integer} id 主表主键
     */

    /**
     * 区表-修改
     * @async
     * @param {UpdateTEcNewoneDistrictRequest} updateTEcNewoneDistrictRequest
     * @return {Promise<UpdateTEcNewoneDistrictResponse>} 返回值
     */
    updateTEcNewoneDistrict = async updateTEcNewoneDistrictRequest => {
        return await this.network.json('/tEcNewoneDistrict/updateTEcNewoneDistrict.do', updateTEcNewoneDistrictRequest);
    }

    /**
     * @typedef {object} AddTEcNewoneDistrictRequest
     * @property {string} [definition] 区名
     * @property {string} [value] 区序号
     */

    /**
     * @typedef {object} AddTEcNewoneDistrictResponse
     * @property {integer} id 主表主键
     */

    /**
     * 区表-新增
     * @async
     * @param {AddTEcNewoneDistrictRequest} addTEcNewoneDistrictRequest
     * @return {Promise<AddTEcNewoneDistrictResponse>} 返回值
     */
    addTEcNewoneDistrict = async addTEcNewoneDistrictRequest => {
        return await this.network.json('/tEcNewoneDistrict/addTEcNewoneDistrict.do', addTEcNewoneDistrictRequest);
    }

    /**
     * 列表查询请求对象
     * @typedef {object} ListRequest
     * @property {object} [page] 分页条件
     * @property {integer} [page.currentPage=&#34;1&#34;] 查询页码
     * @property {integer} [page.linesPerPage=&#34;10&#34;] 每页条数
     * @property {array} [page.orderBys]
     * @property {object} [data]
     * @property {string} [data.definition] 区名
     * @property {string} [data.value] 区序号
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
     * 区表-列表查询
     * @async
     * @param {ListRequest} listRequest
     * @return {Promise<ListResponse>} 返回值
     */
    list = async listRequest => {
        return await this.network.json('/tEcNewoneDistrict/list.do', listRequest);
    }

    /**
* @typedef {object} GetTEcNewoneDistrictRequest
* @property {string} id 区表id
*/
    /**
     * @typedef {object} GetTEcNewoneDistrictResponse
     * @property {integer} [id] 区表ID
     * @property {string} [definition] 区名
     * @property {string} [value] 区序号
     */

    /**
     * 区表-详情获取
     * @async
     * @param {GetTEcNewoneDistrictRequest} getTEcNewoneDistrictRequest
     * @return {Promise<GetTEcNewoneDistrictResponse>} 返回值
     */
    getTEcNewoneDistrict = async getTEcNewoneDistrictRequest => {
        return await this.network.formGet('/tEcNewoneDistrict/getTEcNewoneDistrict.do', getTEcNewoneDistrictRequest);
    }

}
export default TEcNewoneDistrictService;
