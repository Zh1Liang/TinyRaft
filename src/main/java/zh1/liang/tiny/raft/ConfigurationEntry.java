package zh1.liang.tiny.raft;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigurationEntry {

        //该类的对象封装的是当前集群的配置信息，这个配置分为新配置和旧配置，因为集群的配置也是有可能变更的
        //这个类在第一版本其实没有发挥什么作用，因为我们也没有对集群进行配置变更，这是后面版本代码要展示的内容
        //所以，在第一个版本，大家就简单看看这个类即
        //当前生效的配置
        private Configuration conf = new Configuration();
        //旧的配置
        private Configuration oldConf = new Configuration();


        //构造方法
        public ConfigurationEntry() {
            super();
        }

        public Configuration getConf() {
            return this.conf;
        }


        public void setConf(final Configuration conf) {
            this.conf = conf;
        }


        public Configuration getOldConf() {
            return this.oldConf;
        }


        public void setOldConf(final Configuration oldConf) {
            this.oldConf = oldConf;
        }


        //判断旧的配置是否为空，如果旧配置为空，说明当前集群的配置没有进行过变更，也就代表当前集群是很稳定的
        public boolean isStable() {
            return this.oldConf.isEmpty();
        }

        //判断当前配置是否为空
        public boolean isEmpty() {
            return this.conf.isEmpty();
        }

        //把当前配置和就配置中的所有PeerId以集合的形式返回
        public Set<PeerId> listPeers() {
            final Set<PeerId> ret = new HashSet<>(this.conf.listPeers());
            ret.addAll(this.oldConf.listPeers());
            return ret;
        }


        //判断某个PeerId是否存在于当前配置或旧配置中
        public boolean contains(final PeerId peer) {
            return this.conf.contains(peer) || this.oldConf.contains(peer);
        }

        //这个类中也有一些和学习者有关的方法，大家也可以暂时忽略
}
