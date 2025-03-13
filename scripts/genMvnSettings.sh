cat <<EOF | tee $HOME/.m2/settings.xml
<settings>
  <servers>
    <server>
      <id>my-external-repo</id>
      <username>${REPO_USERNAME}</username>
      <password>${REPO_PASSWORD}</password>
    </server>
  </servers>
</settings>
EOF