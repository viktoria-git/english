<button type="button" class="btn btn-dark"
        style="position: relative;left: 15px;"
        data-toggle="modal" data-target="#exampleModal">
    Create a new word
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 400px;height: 400px;">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create a new word</h5>
            </div>
            <form class="form-inline" style="width: 95%" method="post" action="/add">
                <div class="modal-body">
                    <div class="form-group mx-sm-3 mb-2" style="position: relative; left: 60px;">
                        <label class="sr-only">Word</label>
                        <label><input type="text" class="form-control" name="word"
                                      style="height: 38px;width: 210px;" placeholder="Word"
                                      value="${param.word}"></label>
                    </div>

                    <div class="form-group mx-sm-3 mb-2" style="position: relative;left: 60px;">
                        <jsp:include page="./selectTopic.jsp"/>
                    </div>

                    <div class="form-group mx-sm-3 mb-2" style="position: relative;left: 60px;">
                        <jsp:include page="./selectLevel.jsp"/>
                    </div>
                </div>
                <div class="modal-body" style="position: relative;top: 95px;right: 140px;">
                    <button type="submit" class="btn btn-dark">Add</button>
                </div>
            </form>
            <div class="modal-body" style="position: relative;top:20px;">
                <p style="color:black; left: 0">Or you can upload words from the file:</p>
                <form method="post" action="/upload" enctype="multipart/form-data">
                    <div class="input-group">
                        <div class="custom-file">
                            <input type="file" name="file"/><br/><br/>
                        </div>
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-dark">Upload</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>