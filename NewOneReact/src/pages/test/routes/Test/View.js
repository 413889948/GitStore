import styles from './style.scss';
import React, { useMemo } from 'react';
import { Flex, Icon } from '@mshare/mshareui';
import { ActionColumn, Column } from '@share/list';
import { useListPage } from '@share/hooks';
import Header from '@/components/Header';
import AdvanceSearch from '@/components/AdvanceSearch';
import FilterBar from '@/components/FilterBar';
import Form from '@mshare/mshareui-form/builder/Form';
import FilterSelect from '@mshare/mshareui-form/builder/FilterSelect';
import CardList from '@/components/CardList';
import CardItem from './CardItem';
import { useRemove, useToRoute } from './hooks';
import { useService } from '@share/framework';
import TEcNewoneDistrictService from '@/services/TEcNewoneDistrictService';
import Input from '@mshare/mshareui-form/builder/Input';

const Page = () => {
    const service = useService(TEcNewoneDistrictService);
    const {
        listState,
        formState,
        search,
        reset
    } = useListPage({
        searchService: service.list,
        initData: {},
        moreMode: true,
        uniqKey: 'id'
    });
    const {
        toEdit,
        toAdd
    } = useToRoute();
    const {
        remove
    } = useRemove({
        refresh: listState.refresh
    });
    const actions = useMemo(() => {
        return [
            {
                label: '新增',
                handle: toAdd,
                icon: ''
            }
        ];
    }, [toAdd]);

    return (<Flex className={styles.box} direction="column" align="stretch">
        <Header title="列表" actions={actions} />

        <Form formState={formState}>
            <AdvanceSearch field="definition" placeholder="区名" query={search} reset={reset}>


                <Input field="definition" label="区名" />

                <Input field="value" label="区序号" />


            </AdvanceSearch>

            <FilterBar>
                <FilterSelect field="definition" label="区名" />

                <FilterSelect field="definition" label="区名" />
            </FilterBar>

        </Form>

        <CardList listState={listState} CardItem={CardItem}>


            <Column field="id" label="区表ID" />

            <Column field="definition" label="区名" />

            <Column field="value" label="区序号" />


            <ActionColumn mappingField="action">
                <ActionColumn.Edit onClick={toEdit} />
                <ActionColumn.Remove onClick={remove} />
            </ActionColumn>
        </CardList>
    </Flex>);
};

export default Page;
