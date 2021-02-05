import React from 'react';
import {transition} from './hooks'
import  styles from './Home.scss'

transition();
const View = () => {


    return (<div >
        <img src='./newOne/img/wecome.jpg' className={styles.wecomeImg}></img>
    </div>);
};

export default View;
