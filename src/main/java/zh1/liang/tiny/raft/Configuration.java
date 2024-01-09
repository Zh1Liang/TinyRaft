package zh1.liang.tiny.raft;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Configuration {

        //存放PeerId的集合，因为集群中各个节点都会被包装成一个个PeerId对象
        //所有PeerId对象都存放在这个集合中，也就意味着Configuration对象获得了
        //集群中所有节点的信息
        private List<PeerId> peers = new ArrayList<>();

        //下面是构造方法
        public Configuration(final Iterable<PeerId> conf) {
            Requires.requireNonNull(conf, "conf");
            for (final PeerId peer : conf) {
                //把每个节点添加到集合中
                this.peers.add(peer.copy());
            }
        }

        public Configuration copy() {
            return new Configuration(this.peers, this.learners);
        }


        //重制配置信息的方法
        public void reset() {
            this.peers.clear();
        }

        public List<PeerId> listPeers() {
            return new ArrayList<>(this.peers);
        }

        public List<PeerId> getPeers() {
            return this.peers;
        }

        public void setPeers(final List<PeerId> peers) {
            this.peers.clear();
            for (final PeerId peer : peers) {
                this.peers.add(peer.copy());
            }
        }

        public boolean addPeer(final PeerId peer) {
            return this.peers.add(peer);
        }

        //还有很多操纵peers集合的方法，就不再一一展示了，当然，这类中还有一个学习者集合
        //这些概念后面我们都会讲到，大家看我提供的代码时，可以先不关注这个集合
}
